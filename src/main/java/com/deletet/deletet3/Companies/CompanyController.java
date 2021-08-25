package com.deletet.deletet3.Companies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @GetMapping(value = "/company/readAll")
    public ResponseEntity<List<CompanyDTO>> readAll()
    {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for(Company company : companies)
        {
            companyDTOS.add(new CompanyDTO(company.getId(),company.getCompanyName(),company.getCompanyUrl(),company.getCompanyDepartment(),company.getEstablishment(),company.getEmployeeCount(),company.getCompanyAbout(),company.getAddress(),company.getContact(),company.getWebsite()));
        }
        return new ResponseEntity<>(companyDTOS, HttpStatus.OK);
    }

    @PostMapping("/company/create")
    public ResponseEntity<CompanyDTO> create(@RequestBody CompanyDTO request)
    {
        Company company = new Company(request.getId(),request.getCompanyName(),request.getCompanyUrl(),request.getCompanyDepartment(),request.getEstablishment(),request.getEmployeeCount(),request.getCompanyAbout(),request.getAddress(),request.getContact(),request.getWebsite());
        company = companyRepository.save(company);
        return new ResponseEntity<>(new CompanyDTO(company.getId(),company.getCompanyName(),company.getCompanyUrl(),company.getCompanyDepartment(),company.getEstablishment(),company.getEmployeeCount(),company.getCompanyAbout(),company.getAddress(),company.getContact(),company.getWebsite()),HttpStatus.OK);
    }
}
