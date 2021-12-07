package project.Springplayground.web.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.Springplayground.model.UserProfile;
import project.Springplayground.model.UserProfileRepository;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/validation/v3")
public class ValidationControllerV3 {

    //스프링에서 빈 주입
    private final UserProfileRepository userProfileRepository;
    private final UserProfileValidator userProfileValidator;

    //회원가입
    @GetMapping("/joinForm")
    public String addForm(Model model){
        //타임리프로 값을 불러오기 때문에 빈값을 넘겨줘야함
        model.addAttribute("userProfile", new UserProfile());
        return "/validation/v3/joinForm";
    }

    @PostMapping("/joinForm")
    public String addUser(@Validated @ModelAttribute UserProfile userProfile,
                           BindingResult bindingResult,
                           Model model)
    {
        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            //중요
            return "/validation/v3/joinForm";
        }
        //성공 로직
        userProfileRepository.save(userProfile);
        return "/validation/v3/User";
    }
    //회원 수정
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        UserProfile userProfile = userProfileRepository.findById(id);
        model.addAttribute("userProfile", userProfile);
        return "/validation/v3/User";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute UserProfile userProfile){
        System.out.println("id = " + id);
        System.out.println("userProfile = " + userProfile);
        userProfileRepository.update(id, userProfile);
        return "redirect:/validation/v3/searchUser";
    }
    //회원 전체 조회
    @GetMapping("/searchUser")
    public String searchUser(Model model){
        List<UserProfile> userProfile = userProfileRepository.findAll();
        model.addAttribute("items", userProfile);
        return "/validation/v3/searchUser";
    }
    //회원 상세 조회
    @GetMapping("/items/{id}")
    public String item(@PathVariable Long id, Model model) {
        UserProfile userProfile = userProfileRepository.findById(id);
        model.addAttribute("item", userProfile);
        return "/validation/v3/userInform";
    }
}