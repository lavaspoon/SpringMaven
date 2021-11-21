package project.Springplayground.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Springplayground.mapper.CompanyMapper;
import project.Springplayground.mapper.EmployeeMapper;
import project.Springplayground.model.Company;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Company> getAll(){
        List<Company> companyList = companyMapper.getAll();

        if(companyList != null && companyList.size() > 0) {
            for(Company company: companyList) {
                company.setEmployeeList(employeeMapper.getByCompanyId(company.getId()));
            }
        }
        return companyList;
    }
}
