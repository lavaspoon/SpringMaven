package project.Springplayground.controller;

import org.springframework.web.bind.annotation.*;
import project.Springplayground.mapper.UserProfileMapper;
import project.Springplayground.model.UserProfile;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //스프링 프레임워크가 클래스를 컨트롤러로 인식하고 인스턴스를 생성
public class UserProfileController {

    private UserProfileMapper mapper;
    //UserProfileController 생성자로 전달받은 mapper를 내부 mapper 래퍼런스에 저장
    public UserProfileController(UserProfileMapper mapper) {
        this.mapper = mapper;
    }

//    private Map<String, UserProfile> userMap; // < ID(key), UserProfile(value) >

//    @PostConstruct //스프링 프레임워크가 클래스 인스턴스를 만들고 그 직후에 호출함
//    public void init(){
//        userMap = new HashMap<String, UserProfile>();
//        userMap.put("1", new UserProfile("1","홍길동","010-1234-1234","대전시 서구"));
//        userMap.put("2", new UserProfile("2","이은호","010-6595-1234","대전시 동구"));
//        userMap.put("3", new UserProfile("3","김경희","010-7400-1234","대전시 중구"));
//    }

    /**
     * 1) 웹으로 부터 전달받은 id 값을 UserProfileMapper의 getUserProfile로 파라미터 넘김
     * 2) UserProfileMapper의 getUserProfile과 매핑된 sql문이 실행되고, UserProfile 형태로 반환됨
     * 3) UserProfile (JSON) 형태로 반환
     */
    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id) {
//        return userMap.get(id);
        return mapper.getUserProfile(id);
    }

    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList() {
//        return new ArrayList<UserProfile>(userMap.values());
        return mapper.getUserProfileList();
    }

    //데이터 생성
    @PutMapping("/user/put")
    public void putUserProfile(//@PathVariable("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address)
    {
//        UserProfile userProfile = new UserProfile(id,name,phone,address);
//        userMap.put(id, userProfile);
        mapper.insertUserProfile(name, phone, address);
    }

    //데이터 수정
    @PostMapping("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id,
                                @RequestParam("name") String name,
                                @RequestParam("phone") String phone,
                                @RequestParam("address") String address)
    {
//        UserProfile userProfile = userMap.get(id);
//        userProfile.setName(name);
//        userProfile.setPhone(phone);
//        userProfile.setAddress(address);
        mapper.updateUserProfile(id, name, phone, address);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(@PathVariable("id") String id){
//        userMap.remove(id);
        mapper.deleteUserProfile(id);
    }
}
