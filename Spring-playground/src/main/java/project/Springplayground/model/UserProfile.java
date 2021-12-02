package project.Springplayground.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {
    private String id;
    private String name;
    private String phone;
    private String address;
    private Integer point;

    public UserProfile(String id, String name, String phone, String address, Integer point) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.point = point;
    }

    public UserProfile() {
    }
}
