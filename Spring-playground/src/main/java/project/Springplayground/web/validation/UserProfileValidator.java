package project.Springplayground.web.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import project.Springplayground.model.UserProfile;

//컴포넌트 스캔하면 스프링빈에 등록됨
@Component
public class UserProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserProfile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserProfile userProfile = (UserProfile)  target;

        //검증 로직
        if(!StringUtils.hasText(userProfile.getName())){
            errors.rejectValue("name", "required");
        }
        if(!StringUtils.hasText(userProfile.getPhone())){
            errors.rejectValue("phone", "required");
        }
        if(!StringUtils.hasText(userProfile.getAddress())){
            errors.rejectValue("address", "required");
        }
        if(userProfile.getPoint() == null || userProfile.getPoint() < 1000 || userProfile.getPoint() > 1000000){
            errors.rejectValue("point", "range", new Object[]{1000,1000000}, null);
        }
    }
}
