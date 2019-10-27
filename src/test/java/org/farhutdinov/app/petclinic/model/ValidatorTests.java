package org.farhutdinov.app.petclinic.model;

import org.farhutdinov.app.petclinic.model.based.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;


public class ValidatorTests {

    private Validator validator;

//    private Validator createValidator(){
//        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
//        localValidatorFactoryBean.afterPropertiesSet();
//        return localValidatorFactoryBean;
//    }
//
//    @Test
//    public void shouldNotValidateWhenFirstNameEmpty(){
//        LocaleContextHolder.setLocale(Locale.ENGLISH);
//        Person person = new Person();
//        person.setFirstName("");
//        person.setLastName("smith");
//
//        Validator validator = createValidator();
//        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
//
//        assertThat(constraintViolations.size()).isEqualTo(1);
//        ConstraintViolation<Person> violation = constraintViolations.iterator().next();
//        assertThat(violation.getPropertyPath().toString()).isEqualTo("firstName");
//        assertThat(violation.getMessage()).isEqualTo("must not be empty");
//    }

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldNotValidateWhenFirstNameEmptyV2(){

        Person person = new Person();
        person.setFirstName("");
        person.setLastName("Smith");

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertFalse(violations.isEmpty());
        ConstraintViolation<Person> violation = violations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("not be empty");
    }
}
