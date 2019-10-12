package org.farhutdinov.app.petclinic.model;

import org.farhutdinov.app.petclinic.model.based.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity implements Serializable {

}
