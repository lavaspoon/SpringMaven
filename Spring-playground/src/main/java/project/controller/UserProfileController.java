package project.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;
import project.model.UserProfile;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //스프링 프레임워크가 클래스를 컨트롤러로 인식하고 인스턴스를 생성
public class UserProfileController {
    private Map<String, UserProfile> userMap; // < ID(key), UserProfile(value) >

    @PostConstruct //스프링 프레임워크가 클래스 인스턴스를 만들고 그 직후에 호출함
    public void init(){
        userMap = new HashMap<String, UserProfile>();
        userMap.put("1", new UserProfile("1","홍길동","010-1234-1234","대전시 서구"));
        userMap.put("2", new UserProfile("2","이은호","010-6595-1234","대전시 동구"));
        userMap.put("3", new UserProfile("3","김경희","010-7400-1234","대전시 중구"));
    }

    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id) {
        return userMap.get(id);
    }

    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList() {
        return new ArrayList<UserProfile>(userMap.values());
    }

    //데이터 생성
    @PutMapping("/user/{id}")
    public void putUserProfile(@PathVariable("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address)
    {
        UserProfile userProfile = new UserProfile(id,name,phone,address);
        userMap.put(id, userProfile);
    }

    //데이터 수정
    @PostMapping("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id,
                                @RequestParam("name") String name,
                                @RequestParam("phone") String phone,
                                @RequestParam("address") String address)
    {
        UserProfile userProfile = userMap.get(id);
        userProfile.setName(name);
        userProfile.setPhone(phone);
        userProfile.setAddress(address);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(@PathVariable("id") String id){
        userMap.remove(id);
    }
}
