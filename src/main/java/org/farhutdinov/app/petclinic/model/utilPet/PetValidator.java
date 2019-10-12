package org.farhutdinov.app.petclinic.model.utilPet;

import org.farhutdinov.app.petclinic.model.Pet;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PetValidator implements Validator {
    private static final String REQUIRED = "required";

    @Override
    public boolean supports(Class<?> aClass) {
        return Pet.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object , Errors errors) {
        Pet pet = (Pet) object;
        String name = pet.getName();

        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name",REQUIRED,REQUIRED);
        }

        if (pet.isNew() && pet.getType() == null) {
            errors.rejectValue("type", REQUIRED, REQUIRED);
        }

        if (pet.getBirthDate() == null) {
            errors.rejectValue("birthDate", REQUIRED, REQUIRED);
        }
    }
}
