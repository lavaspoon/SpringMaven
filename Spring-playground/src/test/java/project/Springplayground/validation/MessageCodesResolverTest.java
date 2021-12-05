package project.Springplayground.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {
    //구현체 : DefaultMessageCodesResolver
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "userProfile");
        Assertions.assertThat(messageCodes).contains("required.item", "required");
    }
    @Test
    void messageCodesResolverField(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "userProfile", "name", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        new FieldError("userProfile", "name", null, false, messageCodes, null, null);
        Assertions.assertThat(messageCodes).contains(
                "required.userProfile.name",
                "required.name",
                "required.java.lang.String",
                "required"
        );
    }
}
