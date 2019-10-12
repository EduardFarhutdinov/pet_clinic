package org.farhutdinov.app.petclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.farhutdinov.app.petclinic.model.based.BasedEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
@Data
//@NoArgsConstructor
public class Visit extends BasedEntity {
    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @Column(name = "pet_id")
    private Integer petId;

    public Visit() {
        this.date = LocalDate.now();
    }
}
