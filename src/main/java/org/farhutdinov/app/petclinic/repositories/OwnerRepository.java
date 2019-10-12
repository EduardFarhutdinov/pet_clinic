package org.farhutdinov.app.petclinic.repositories;

import org.farhutdinov.app.petclinic.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner,Long> {

    @Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    Collection<Owner> findByLastName(@Param("lastName") String lastName);
//
//    @Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
//    @Transactional(readOnly = true)
//    Owner findById(@Param("id") Integer id);

    @Transactional(readOnly = true)
    Owner findById(Integer id);
}
