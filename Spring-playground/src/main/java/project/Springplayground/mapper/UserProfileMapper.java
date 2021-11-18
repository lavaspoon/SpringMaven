package project.Springplayground.mapper;

import org.apache.ibatis.annotations.*;
import project.Springplayground.model.UserProfile;

import java.util.List;

@Mapper // 스프링부트에서 mapper라고 인식하기 위해서
public interface UserProfileMapper {
    //Controller에서 전달된 데이터로 SQL문을 실행하여 UserProfile 객체 반환
    @Select("SELECT * FROM UserProfile WHERE id=#{id}") //매핑할 SQL문(@Param으로 매핑하려면 $기 아닌 #을 쓴다)
    UserProfile getUserProfile(@Param("id") String id); //@Param 어노테이션으로 인해 전달받은 id값이 sql에문에 들어감

    //SQL문을 실행하여 List 형태로 반환
    @Select("SELECT * FROM UserProfile")
    List<UserProfile> getUserProfileList();

    //int 형 반환: 영향받은 레코드 수(1 인지 확인하여 정상적으로 처리됐는지 확인)
    @Insert("INSERT INTO UserProfile VALUES(#{id}, #{name}, #{phone}, #{address})")
    int insertUserProfile(@Param("id") String id,
                       @Param("name") String name,
                       @Param("phone") String phone,
                       @Param("address") String address);

    @Update("UPDATE UserProfile SET name=#{name}, phone=#{phone}, address=#{address} where id=#{id}")
    int updateUserProfile(@Param("id") String id,
                        @Param("name") String name,
                        @Param("phone") String phone,
                        @Param("address") String address);

    @Delete("DELETE FROM UserProfile WHERE id=#{id}")
    int deleteUserProfile(@Param("id") String id);
}
