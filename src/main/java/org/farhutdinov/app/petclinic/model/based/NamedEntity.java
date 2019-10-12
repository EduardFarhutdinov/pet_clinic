package org.farhutdinov.app.petclinic.model.based;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedEntity extends BasedEntity{

    @Column(name = "name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
