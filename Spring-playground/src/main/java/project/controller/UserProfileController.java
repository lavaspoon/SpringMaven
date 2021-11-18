package project.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;
import project.model.UserProfile;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserProfileController {
    private Map<String, UserProfile> userMap;

    @PostConstruct
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

    @PutMapping("/user/{id}")
    public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
        UserProfile userProfile = new UserProfile(id,name,phone,address);
        userMap.put(id, userProfile);
    }

    @PostMapping("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id,
                                @RequestParam("name") String name,
                                @RequestParam("phone") String phone,
                                @RequestParam("address") String address) {
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
