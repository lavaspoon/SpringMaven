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

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(userProfileValidator);
    }
    //회원가입
    @GetMapping("/joinForm")
    public String addForm(Model model){
        //타임리프로 값을 불러오기 때문에 빈값을 넘겨줘야함
        model.addAttribute("userProfile", new UserProfile());
        return "/validation/v3/joinForm";
    }

    //@PostMapping("/joinForm")
    public String addUser1(@ModelAttribute UserProfile userProfile,
                          BindingResult bindingResult,
                          Model model)
    {
        //검증 로직
        if(!StringUtils.hasText(userProfile.getName())){
            bindingResult.addError(new FieldError("userProfile", "name", "이름을 입력해주세요."));
        }
        if(!StringUtils.hasText(userProfile.getPhone())){
            bindingResult.addError(new FieldError("userProfile", "phone", "핸드폰번호를 입력해주세요."));
        }
        if(!StringUtils.hasText(userProfile.getAddress())){
            bindingResult.addError(new FieldError("userProfile", "address", "주소를 입력해주세요."));
        }
        if(userProfile.getPoint() == null || userProfile.getPoint() < 1000 || userProfile.getPoint() > 1000000){
            bindingResult.addError(new FieldError("userProfile", "point", "포인트는 최소 1000, 1,000,000 미만으로 입력해주세요."));
        }

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

    //@PostMapping("/joinForm")
    public String addUser2(@ModelAttribute UserProfile userProfile,
                           BindingResult bindingResult,
                           Model model)
    {
        //검증 로직
        if(!StringUtils.hasText(userProfile.getName())){
            bindingResult.addError(new FieldError("userProfile", "name", userProfile.getName(), false, null, null,"이름을 입력해주세요."));
        }
        if(!StringUtils.hasText(userProfile.getPhone())){
            bindingResult.addError(new FieldError("userProfile", "phone", userProfile.getPhone(), false, null, null,"핸드폰번호를 입력해주세요."));
        }
        if(!StringUtils.hasText(userProfile.getAddress())){
            bindingResult.addError(new FieldError("userProfile", "address", userProfile.getAddress(), false, null, null,"주소를 입력해주세요."));
        }
        if(userProfile.getPoint() == null || userProfile.getPoint() < 1000 || userProfile.getPoint() > 1000000){
            bindingResult.addError(new FieldError("userProfile", "point", userProfile.getPoint(), false, null, null,"포인트는 최소 1000, 1,000,000 미만으로 입력해주세요."));
        }

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

    //@PostMapping("/joinForm")
    public String addUser3(@ModelAttribute UserProfile userProfile,
                           BindingResult bindingResult,
                           Model model)
    {
        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());
        //검증 로직
        if(!StringUtils.hasText(userProfile.getName())){
            bindingResult.addError(new FieldError("userProfile", "name", userProfile.getName(), false, new String[]{"required.userProfile.name"}, null,null));
        }
        if(!StringUtils.hasText(userProfile.getPhone())){
            bindingResult.addError(new FieldError("userProfile", "phone", userProfile.getPhone(), false, new String[]{"required.userProfile.phone"}, null,null));
        }
        if(!StringUtils.hasText(userProfile.getAddress())){
            bindingResult.addError(new FieldError("userProfile", "address", userProfile.getAddress(), false, new String[]{"required.userProfile.address"}, null,null));
        }
        if(userProfile.getPoint() == null || userProfile.getPoint() < 1000 || userProfile.getPoint() > 1000000){
            bindingResult.addError(new FieldError("userProfile", "point", userProfile.getPoint(), false, new String[]{"range.userProfile.point"}, new Object[]{1000, 1000000},null));
        }

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

    //@PostMapping("/joinForm")
    public String addUser4(@ModelAttribute UserProfile userProfile,
                           BindingResult bindingResult,
                           Model model)
    {
        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());
        //검증 로직
        if(!StringUtils.hasText(userProfile.getName())){
            bindingResult.rejectValue("name", "required");
        }
        if(!StringUtils.hasText(userProfile.getPhone())){
            bindingResult.rejectValue("phone", "required");
        }
        if(!StringUtils.hasText(userProfile.getAddress())){
            bindingResult.rejectValue("address", "required");
        }
        if(userProfile.getPoint() == null || userProfile.getPoint() < 1000 || userProfile.getPoint() > 1000000){
            bindingResult.rejectValue("point", "range", new Object[]{1000,1000000}, null);
        }

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

    //@PostMapping("/joinForm")
    public String addUser5(@ModelAttribute UserProfile userProfile,
                           BindingResult bindingResult,
                           Model model)
    {
        userProfileValidator.validate(userProfile, bindingResult);


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

    @PostMapping("/joinForm")
    public String addUser6(@Validated @ModelAttribute UserProfile userProfile,
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