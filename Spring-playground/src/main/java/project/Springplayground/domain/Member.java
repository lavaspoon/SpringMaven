package project.Springplayground.domain;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id;
    private String loginId;
    private String name;
    private String password;
    private Integer point;
}
