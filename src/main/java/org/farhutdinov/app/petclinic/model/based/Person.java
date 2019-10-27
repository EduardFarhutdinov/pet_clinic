package org.farhutdinov.app.petclinic.model.based;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
@Data
public class Person extends BasedEntity {

    @Column(name = "first_name")
    @NotEmpty(message = "not be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;


}
