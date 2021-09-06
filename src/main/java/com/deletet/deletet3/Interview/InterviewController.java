package com.deletet.deletet3.Interview;


import com.deletet.deletet3.Applications.Application;
import com.deletet.deletet3.Applications.ApplicationRepository;
import com.deletet.deletet3.Companies.Company;
import com.deletet.deletet3.Companies.CompanyRepository;
import com.deletet.deletet3.Profile.Profile;
import com.deletet.deletet3.Profile.ProfileDTO;
import com.deletet.deletet3.Profile.ProfileRepository;
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
    private final ProfileRepository profileRepository;
    private final ApplicationRepository applicationRepository;
    private Company company;
    private AppUser appuser;

    public InterviewController(InterviewRepository interviewRepository, AppUserRepository appUserRepository, CompanyRepository companyRepository, ProfileRepository profileRepository, ApplicationRepository applicationRepository){
        this.InterviewRepository = interviewRepository;
        this.appUserRepository = appUserRepository;
        this.companyRepository = companyRepository;
        this.profileRepository = profileRepository;
        this.applicationRepository = applicationRepository;
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
                if(interview.getCompanyId().equals(company.getId()))
                {
                    interviewDTOS.add(new InterviewDTO(interview.getId(), interview.getApplicantName(), interview.getApplicantId(),interview.getApplicantIcon(),interview.getCompanyName(),interview.getCompanyId(),interview.getCompanyIcon(),
                            interview.getApplicationId(),interview.getTitle(), interview.getApplicationDesc(),interview.getCompanyOfficer(),interview.getCompanyDepartment(),interview.getPosition(),
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
                if(interview.getApplicantId().equals(appuser.getId()))
                {
                    interviewDTOS.add(new InterviewDTO(interview.getId(), interview.getApplicantName(), interview.getApplicantId(),interview.getApplicantIcon(),interview.getCompanyName(),interview.getCompanyId(),interview.getCompanyIcon(),
                            interview.getApplicationId(),interview.getTitle(), interview.getApplicationDesc(),interview.getCompanyOfficer(),interview.getCompanyDepartment(),interview.getPosition(),
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

    @PostMapping("/interview/create")
    public ResponseEntity<InterviewDTO> create(@RequestBody InterviewDTO request)
    {
        Optional<Company> tempCompany = companyRepository.findById(request.getCompanyId());
        Company company = tempCompany.get();
        Optional<AppUser> tempUser = appUserRepository.findById(request.getApplicantId());
        AppUser appuser = tempUser.get();
        Optional<Application> tempApp = applicationRepository.findById(request.getApplicationId());
        Application application = tempApp.get();
        Profile profile = null;
        List<Profile> profiles = profileRepository.findAll();
        for(Profile tempProfile : profiles)
        {
            if(tempProfile.getUserId().equals(request.getApplicantId()))
            {
                profile = tempProfile;
            }
        }
        Interview interview = new Interview(profile.getFullName(),request.getApplicantId(),profile.getImageUrl(),company.getCompanyName(),
                request.getCompanyId(),company.getCompanyUrl(),request.getApplicationId(),application.getAdvertsTitle(),application.getAdvertsDescription(),request.getCompanyOfficer(),request.getCompanyDepartment(),
                request.getPosition(),request.getInterDate(),application.getCompanyLocation(),request.getApplicationStatus(),request.getInterStatus());
        interview = InterviewRepository.save(interview);
        return new ResponseEntity<>(new InterviewDTO(interview.getId(), interview.getApplicantName(), interview.getApplicantId(),interview.getApplicantIcon(), interview.getCompanyName(),
                interview.getCompanyId(), interview.getCompanyIcon(),request.getApplicationId(),interview.getTitle(),interview.getApplicationDesc(), interview.getCompanyOfficer(), interview.getCompanyDepartment(),
                interview.getPosition(),interview.getInterDate(),interview.getLocation(),interview.getApplicationStatus(), interview.getInterStatus()), HttpStatus.OK);

    }

    @PutMapping("/interview/updateStatus/{id}")
    public ResponseEntity<Interview> updateStatus (@PathVariable Long id, @RequestBody InterviewDTO request)
    {
        Optional<Interview> interview = InterviewRepository.findById(id);
        Interview inter = interview.get();
        inter.setInterStatus(request.getInterStatus());
        inter = InterviewRepository.save(inter);

        return new ResponseEntity<>(inter,HttpStatus.OK);
    }







}
