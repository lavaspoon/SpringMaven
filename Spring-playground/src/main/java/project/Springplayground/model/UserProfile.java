package project.Springplayground.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfile {
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private String phone;

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
