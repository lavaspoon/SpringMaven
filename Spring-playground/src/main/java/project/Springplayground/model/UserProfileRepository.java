package project.Springplayground.model;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserProfileRepository {

    private static final Map<Long, UserProfile> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public UserProfile save(UserProfile userProfile) {
        userProfile.setId(++sequence);
        store.put(userProfile.getId(), userProfile);
        return userProfile;
    }

    public UserProfile findById(Long id) {
        return store.get(id);
    }

    public List<UserProfile> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, UserProfile updateParam) {
        UserProfile findItem = findById(id);
        findItem.setName(updateParam.getName());
        findItem.setPhone(updateParam.getAddress());
        findItem.setPhone(updateParam.getPhone());
        findItem.setPoint(updateParam.getPoint());
    }

    public void clearStore() {
        store.clear();
    }

}
