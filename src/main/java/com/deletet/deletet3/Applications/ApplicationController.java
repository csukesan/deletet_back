package com.deletet.deletet3.Applications;

import com.deletet.deletet3.Adverts.Adverts;
import com.deletet.deletet3.Adverts.AdvertsDTO;
import com.deletet.deletet3.Adverts.AdvertsRepository;
import com.deletet.deletet3.Companies.Company;
import com.deletet.deletet3.Companies.CompanyRepository;
import com.deletet.deletet3.appuser.AppUser;
import com.deletet.deletet3.appuser.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class ApplicationController {

    private AppUser appuser;
    private Company company;
    private AppUserRepository appUserRepository;
    private CompanyRepository companyRepository;
    private AdvertsRepository advertsRepository;

    private final ApplicationRepository applicationRepository;

    public ApplicationController(AppUserRepository appUserRepository, ApplicationRepository applicationRepository, CompanyRepository companyRepository, AdvertsRepository advertsRepository){
        this.appUserRepository = appUserRepository;
        this.applicationRepository = applicationRepository;
        this.companyRepository = companyRepository;
        this.advertsRepository = advertsRepository;
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<List<ApplicationDTO>> read(@PathVariable Long id)
    {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if(appUser.isPresent())
        {
            appuser = appUser.get();
            List<Application> application = applicationRepository.findAll();
            List<ApplicationDTO> applicationDTOS = new ArrayList<>();
            for(Application app : application)
            {
                if(app.getApplicantId().equals(appuser.getId()))
                {
                    applicationDTOS.add(new ApplicationDTO(app.getId(),app.getApplicantId(),app.getCompanyId(), app.getAdvertId(), app.getCompanyName(),app.getCompanyLocation(), app.getCompanyIcon(), app.getCompanyDesc(), app.getApplicationDate(),app.getWayOfWorking(),app.getAdvertsTitle(),app.getAdvertsDescription(), app.getAdvertsAbout(),app.getStatus()));
                }
            }
            return new ResponseEntity<>(applicationDTOS, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/application/create")
    public ResponseEntity<ApplicationDTO> create(@RequestBody ApplicationDTO request)
    {

        Optional<Company> tempCompany = companyRepository.findById(request.getCompanyId());
        Company company = tempCompany.get();
        Optional<Adverts> tempAdvert = advertsRepository.findById(request.getAdvertId());
        Adverts advert = tempAdvert.get();

        LocalDateTime mydate = LocalDateTime.now();
        DateTimeFormatter myFormatdate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = mydate.format(myFormatdate);

        Application application = new Application(request.getApplicantId(),request.getCompanyId(), request.getAdvertId(),
                company.getCompanyName(), company.getAddress(), company.getCompanyUrl(), company.getCompanyAbout(), formattedDate,
                advert.getWayOfWorking(),advert.getAdvertsTitle(),advert.getAdvertsDescription(), advert.getAdvertsAbout(),request.getStatus());
        application = applicationRepository.save(application);
        return new ResponseEntity<>(new ApplicationDTO(application.getId(),application.getApplicantId(), application.getCompanyId(), application.getAdvertId(),application.getCompanyName(),application.getCompanyLocation(),application.getCompanyIcon(),application.getCompanyDesc(),application.getApplicationDate(),application.getWayOfWorking(),application.getAdvertsTitle(),application.getAdvertsDescription(), application.getAdvertsAbout(),application.getStatus()),HttpStatus.OK);
    }


    @GetMapping("/application/company/{id}")
    public ResponseEntity<List<ApplicationDTO>> getApplications(@PathVariable Long id)
    {
        Optional<Company> company1 = companyRepository.findById(id);
        if(company1.isPresent())
        {
            company = company1.get();
            List<Application> applications = applicationRepository.findAll();
            List<ApplicationDTO> applicationDTOS = new ArrayList<>();
            for(Application application : applications)
            {
                if(application.getCompanyId().equals(company.getId()))
                {
                    applicationDTOS.add(new ApplicationDTO(application.getId(), application.getApplicantId(), application.getCompanyId(), application.getAdvertId(), application.getCompanyName(),application.getCompanyLocation(),application.getCompanyIcon(),application.getCompanyDesc(),application.getApplicationDate(),application.getWayOfWorking(),application.getAdvertsTitle(),application.getAdvertsDescription(), application.getAdvertsAbout(),application.getStatus()));
                }
            }
            return new ResponseEntity<>(applicationDTOS, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}


