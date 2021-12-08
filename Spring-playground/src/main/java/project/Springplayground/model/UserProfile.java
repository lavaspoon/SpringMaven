package project.Springplayground.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfile {
    private Long id;

    @NotBlank
    @NotNull(message = "공백 입니다.")
    private String name;

    @NotBlank
    @NotNull
    private String phone;

    @NotBlank
    @NotNull
    private String address;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer point;

    public UserProfile(String name, String phone, String address, Integer point) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.point = point;
    }

    public UserProfile() {
    }
}
