package project.Springplayground.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import project.Springplayground.mapper.UserProfileMapper;
import project.Springplayground.model.UserProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/basic")
public class BasicController {

    private UserProfileMapper mapper;

    public BasicController(UserProfileMapper mapper) {
        this.mapper = mapper;
    }

    //회원가입
    @GetMapping("/joinForm")
    public String addForm(Model model){
        //타임리프로 값을 불러오기 때문에 빈값을 넘겨줘야함
        model.addAttribute("userProfile", new UserProfile());
        return "/basic/joinForm";
    }

    @PostMapping("/joinForm")
    public String addUser(@RequestParam String name,
                          @RequestParam String phone,
                          @RequestParam String address,
                          @RequestParam(required = false) Integer point,
                          Model model)
    {
        UserProfile userProfile = new UserProfile();
        userProfile.setName(name);
        userProfile.setPhone(phone);
        userProfile.setAddress(address);
        userProfile.setPoint(point);
        model.addAttribute("userProfile",userProfile);

        //검증 오류 결과를 보관
        Map<String, String> errors = new HashMap<>();
        //검증 로직
        if(!StringUtils.hasText(userProfile.getName())){
            errors.put("nameError", "이름은 필수 값 입니다.");
        }
        if(!StringUtils.hasText(userProfile.getPhone())){
            errors.put("phoneError", "핸드폰 번호는 필수 값 입니다.");
        }
        if(!StringUtils.hasText(userProfile.getAddress())){
            errors.put("addressError", "주소는 필수 값 입니다.");
        }
        if(userProfile.getPoint() == null || userProfile.getPoint() < 1000 || userProfile.getPoint() > 1000000){
            errors.put("pointError", "포인트는 최소 1000, 1,000,000 입니다.");
        }

        //검증에 실패하면 다시 입력 폼으로
        if(!errors.isEmpty()){
            log.info("errors = {}", errors);
            model.addAttribute("errors",errors);
            //중요
            return "/basic/joinForm";
        }

        //성공 로직
        mapper.insertUserProfile(name, phone, address, point);
        return "/basic/User";
        //redirectAttributes.addAttribute("userId",userProfile.getId()); getId 모름
        //return "redirect:/basic/User/{userId}";
    }

//    @PostMapping("/joinForm")
    public String addUserV2(@RequestParam String name,
                            @RequestParam String phone,
                            @RequestParam String address,
                            @RequestParam Integer point,
                            BindingResult bindingResult,
                            Model model)
    {
        UserProfile userProfile = new UserProfile();
        userProfile.setName(name);
        userProfile.setName(phone);
        userProfile.setName(address);
        userProfile.setPoint(point);
        model.addAttribute("userProfile", userProfile);

        if(!StringUtils.hasText(userProfile.getName())){
            bindingResult.addError(new FieldError("userProfile","name","이름은 필수 값 입니다."));
        }
        if(!StringUtils.hasText(userProfile.getPhone())){
            bindingResult.addError(new FieldError("userProfile","phone","핸드폰은 필수 값 입니다."));
        }
        if(!StringUtils.hasText(userProfile.getAddress())){
            bindingResult.addError(new FieldError("userProfile","address","주소는 필수 값 입니다."));
        }

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "/basic/joinForm";
        }

        mapper.insertUserProfile(name, phone, address, point);
        return "/basic/User";
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