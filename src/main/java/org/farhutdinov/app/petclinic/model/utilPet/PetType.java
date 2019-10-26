package org.farhutdinov.app.petclinic.model.utilPet;

import lombok.NoArgsConstructor;
import org.farhutdinov.app.petclinic.model.based.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends NamedEntity {
}
