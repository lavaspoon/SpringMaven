package project.Springplayground.mapper;

import org.apache.ibatis.annotations.*;
import project.Springplayground.model.Company;
import project.Springplayground.model.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Insert("INSERT INTO employee(company_id, employee_name, employee_address) VALUES(#{employee.companyId} ,#{employee.name}, #{employee.address})")//생성되는 ID 값도 볼려면 아래처럼 option 을 세팅
    @Options(useGeneratedKeys = true, keyProperty = "id")//생성된 키를 id 라는 프로퍼티에 설정해달라는 옵션 지정
    int insert(@Param("employee") Employee employee);

    @Select("SELECT * FROM employee")
    //DB칼럼명과 구조체의 프로퍼티 이름이 서로 다른경우 매핑 해줘야함 (company_name != name)
    @Results(id="EmployeeMap", value = {
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "name", column = "employee_name"),
            @Result(property = "address", column = "employee_address")
    })
    List<Employee> getAll();

    @Select("SELECT * FROM employee where id = #{id}")
    @ResultMap("EmployeeMap")
    Employee getById(@Param("id") int id);

    @Select("SELECT * FROM employee WHERE company_id = #{companyId}")
    @ResultMap("EmployeeMap")
    List<Employee> getByCompanyId(@Param("companyId") int companyId);
}
