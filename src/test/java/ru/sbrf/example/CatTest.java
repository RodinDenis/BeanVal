package ru.sbrf.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class CatTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        //for example not for prod
        log.info("hello");
    }

    @Test
    void getName() {
        Cat cat = new Cat( null, "2", 2 );
        //Cat cat = new Cat( "Boris", "2", 1 );

        Set<ConstraintViolation<Cat>> constraintViolations =
                validator.validate( cat );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void getAge() {
        Cat cat = new Cat( "Boris", "1", 2 );

        Set<ConstraintViolation<Cat>> constraintViolations =
                validator.validate( cat );

        assertEquals( 1, constraintViolations.size() );

        assertEquals(
                "size must be between 2 and 14",
                constraintViolations.iterator().next().getMessage()
        );
    }

}