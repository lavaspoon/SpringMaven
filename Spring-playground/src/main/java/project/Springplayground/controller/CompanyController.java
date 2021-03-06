package project.Springplayground.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.Springplayground.mapper.CompanyMapper;
import project.Springplayground.model.Company;
import project.Springplayground.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @PostMapping("")
    public Company post(@RequestBody Company company) {
        companyMapper.insert(company);
        return company;
    }

    @GetMapping("")
    public List<Company> getAll(){
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable("id") int id){
        return companyMapper.getById(id);
    }
}
