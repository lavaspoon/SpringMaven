package project.Springplayground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.Springplayground.mapper.UserProfileMapper;
import project.Springplayground.model.UserProfile;

import java.util.List;

@Controller
@RequestMapping("/basic")
public class BasicController {

    private UserProfileMapper mapper;

    public BasicController(UserProfileMapper mapper) {
        this.mapper = mapper;
    }

    //회원가입
    @GetMapping("/joinForm")
    public String addForm(){
        return "/basic/joinForm";
    }

    @PostMapping("/joinForm")
    public String addUser(@RequestParam String name,
                          @RequestParam String phone,
                          @RequestParam String address,
                          Model model)
    {
        UserProfile userProfile = new UserProfile();
        userProfile.setName(name);
        userProfile.setPhone(phone);
        userProfile.setAddress(address);

        mapper.insertUserProfile(name, phone, address);
        model.addAttribute("userProfile",userProfile);
        return "basic/User";
    }

    //회원 전체 조회
    @GetMapping("/searchUser")
    public String searchUser(Model model){
        List<UserProfile> userProfileList = mapper.getUserProfileList();
        model.addAttribute("items", userProfileList);
        return "basic/searchUser";
    }
    //회원 상세 조회
    @GetMapping("/items/{id}")
    public String item(@PathVariable String id, Model model) {
        UserProfile userProfile = mapper.getUserProfile(id);
        model.addAttribute("item", userProfile);
        return "basic/userInform";
    }

}