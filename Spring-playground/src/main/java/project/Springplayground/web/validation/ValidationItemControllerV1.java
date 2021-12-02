package project.Springplayground.web.validation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/validation/v1/items")
public class ValidationItemControllerV1 {
    @GetMapping
    public String items(){
        return "validation/v1/items";
    }
}
