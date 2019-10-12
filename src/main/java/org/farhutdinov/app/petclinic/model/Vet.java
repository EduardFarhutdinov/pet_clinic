package org.farhutdinov.app.petclinic.model;

import org.farhutdinov.app.petclinic.model.based.Person;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name= "specialty_id"))
    private Set<Specialty> specialties;


    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Set<Specialty> getSpecialties() {
        if(this.specialties == null){
            this.specialties = new HashSet<>();
        }
        return this.specialties;
    }

    //
//    public Set<Specialty> getSpecialtiesInternal() {
//        if(this.specialties == null){
//            this.specialties = new HashSet<>();
//        }
//        return this.specialties;
//    }
//
//    public void setSpecialtiesInternal(Set<Specialty> specialties) {
//        this.specialties = specialties;
//    }

    @XmlElement
    public List<Specialty> getSpecialtiesInternal() {
        List<Specialty> sortedSpecs = new ArrayList<>(getSpecialties());
        PropertyComparator.sort(sortedSpecs,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public int getNrOfSpecialties() {
        return getSpecialties().size();
    }

    public void addSpecialty(Specialty specialty) {
        getSpecialties().add(specialty);
    }

}
