package org.farhutdinov.app.petclinic.repositories;

import org.farhutdinov.app.petclinic.model.Pet;
import org.farhutdinov.app.petclinic.model.utilPet.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet,Long> {

    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    List<PetType> findPetTypes();

    @Transactional(readOnly = true)
    Pet findById(Integer id);


}
