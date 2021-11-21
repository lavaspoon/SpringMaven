package project.Springplayground.mapper;

import org.apache.ibatis.annotations.*;
import project.Springplayground.model.Company;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Insert("INSERT INTO company(company_name, company_address) VALUES(#{company.name}, #{company.address})")//생성되는 ID 값도 볼려면 아래처럼 option 을 세팅
    @Options(useGeneratedKeys = true, keyProperty = "id")//생성된 키를 id 라는 프로퍼티에 설정해달라는 옵션 지정
    int insert(@Param("company") Company company);

    @Select("SELECT * FROM company")
    //DB칼럼명과 구조체의 프로퍼티 이름이 서로 다른경우 매핑 해줘야함 (company_name != name)
    @Results(id="CompanyMap", value = {
            @Result(property = "name", column = "company_name"),
            @Result(property = "address", column = "company_address")
    })
    List<Company> getAll();

    @Select("SELECT * FROM company where id = #{id}")
    @ResultMap("CompanyMap")
    Company getById(@Param("id") int id);
}

