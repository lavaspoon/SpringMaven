package project.Springplayground.validation;

import org.junit.jupiter.api.Test;
import project.Springplayground.model.UserProfile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UserProfile userProfile = new UserProfile();
        userProfile.setName(" "); //공백
        userProfile.setPhone("");
        userProfile.setAddress("Hi");
        userProfile.setPoint(0);

        Set<ConstraintViolation<UserProfile>> validations = validator.validate(userProfile);
        for (ConstraintViolation<UserProfile> validation : validations) {
            System.out.println("validation = " + validation);
            System.out.println("validation.getMessage() = " + validation.getMessage());
        }
    }
}
