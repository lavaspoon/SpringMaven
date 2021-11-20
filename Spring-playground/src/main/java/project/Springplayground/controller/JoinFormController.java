package project.Springplayground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.Springplayground.mapper.UserProfileMapper;
import project.Springplayground.model.UserProfile;

@Controller
@RequestMapping("/basic")
public class JoinFormController {

    private UserProfileMapper mapper;
    //UserProfileController 생성자로 전달받은 mapper를 내부 mapper 래퍼런스에 저장
    public JoinFormController(UserProfileMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/joinForm")
    public String addForm(){
        return "/basic/joinForm";
    }

    @PostMapping("/joinForm")
    public void addUser(@RequestParam String name,
                          @RequestParam String phone,
                          @RequestParam String address)
    {
        System.out.println("JoinFormController.addUser");
        mapper.insertUserProfile(name, phone, address);
    }
}