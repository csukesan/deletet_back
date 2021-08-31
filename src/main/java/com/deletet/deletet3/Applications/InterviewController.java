package com.deletet.deletet3.Applications;


import com.deletet.deletet3.Companies.Company;
import com.deletet.deletet3.Companies.CompanyRepository;
import com.deletet.deletet3.appuser.AppUser;
import com.deletet.deletet3.appuser.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class InterviewController {


    private final InterviewRepository InterviewRepository;
    private final AppUserRepository appUserRepository;
    private final CompanyRepository companyRepository;
    private Company company;
    private AppUser appuser;

    public InterviewController(InterviewRepository interviewRepository, AppUserRepository appUserRepository, CompanyRepository companyRepository){
        this.InterviewRepository = interviewRepository;
        this.appUserRepository = appUserRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/interview/company/{id}")
    public ResponseEntity<List<InterviewDTO>> getCompanyInterviews(@PathVariable Long id)
    {
        Optional<Company> company1 = companyRepository.findById(id);
        if(company1.isPresent())
        {
            company = company1.get();
            List<Interview> interviews = InterviewRepository.findAll();
            List<InterviewDTO> interviewDTOS = new ArrayList<>();
            for(Interview interview : interviews)
            {
                if(interview.getCompanyId()==company.getId())
                {
                    interviewDTOS.add(new InterviewDTO(interview.getId(), interview.getApplicantName(), interview.getApplicantId(),interview.getApplicantIcon(),interview.getCompanyName(),interview.getCompanyId(),interview.getCompanyIcon(),
                            interview.getTitle(), interview.getApplicationDesc(),interview.getCompanyOfficer(),interview.getCompanyDepartment(),interview.getPosition(),
                            interview.getInterDate(),interview.getLocation(),interview.getApplicationStatus(),interview.getInterStatus()));
                }
            }
            return new ResponseEntity<>(interviewDTOS, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/interview/user/{id}")
    public ResponseEntity<List<InterviewDTO>> getUserInterviews(@PathVariable Long id)
    {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if(appUser.isPresent())
        {
            appuser = appUser.get();
            List<Interview> interviews = InterviewRepository.findAll();
            List<InterviewDTO> interviewDTOS = new ArrayList<>();
            for(Interview interview : interviews)
            {
                if(interview.getApplicantId()==appuser.getId())
                {
                    interviewDTOS.add(new InterviewDTO(interview.getId(), interview.getApplicantName(), interview.getApplicantId(),interview.getApplicantIcon(),interview.getCompanyName(),interview.getCompanyId(),interview.getCompanyIcon(),
                            interview.getTitle(), interview.getApplicationDesc(),interview.getCompanyOfficer(),interview.getCompanyDepartment(),interview.getPosition(),
                            interview.getInterDate(),interview.getLocation(),interview.getApplicationStatus(),interview.getInterStatus()));
                }
            }
            return new ResponseEntity<>(interviewDTOS, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }







}
