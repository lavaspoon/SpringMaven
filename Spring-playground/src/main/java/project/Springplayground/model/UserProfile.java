package project.Springplayground.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {
    private Long id;
    private String name;
    private String phone;
    private String address;
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
