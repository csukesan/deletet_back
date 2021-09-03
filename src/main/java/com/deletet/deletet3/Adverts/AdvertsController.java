package com.deletet.deletet3.Adverts;


import com.deletet.deletet3.Applications.Application;
import com.deletet.deletet3.Applications.ApplicationDTO;
import com.deletet.deletet3.Applications.ApplicationRepository;
import com.deletet.deletet3.Companies.Company;
import com.deletet.deletet3.Companies.CompanyDTO;
import com.deletet.deletet3.Companies.CompanyRepository;
import com.deletet.deletet3.appuser.AppUser;
import com.deletet.deletet3.appuser.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class AdvertsController {

    private Company company;
    private final AdvertsRepository advertsRepository;
    private CompanyRepository companyRepository;
    private ApplicationRepository applicationRepository;
   // private Application application;
    private AppUserRepository appUserRepository;


    public AdvertsController(CompanyRepository companyRepository, AdvertsRepository advertsRepository, ApplicationRepository applicationRepository, AppUserRepository appUserRepository){
        this.companyRepository = companyRepository;
        this.advertsRepository = advertsRepository;
        this.applicationRepository = applicationRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping(value = "/adverts/readAll")
    public ResponseEntity<List<AdvertsDTO>> readAll()
    {
        List<Adverts> adverts = advertsRepository.findAll();
        List<AdvertsDTO> advertsDTOS = new ArrayList<>();
        for(Adverts advert : adverts)
        {
            advertsDTOS.add(new AdvertsDTO(advert.getId(),advert.getCategoryId(),advert.getCompanyId(),advert.getCompanyName(),advert.getCompanyLocation(), advert.getCompanyIcon(), advert.getCompanyDesc(), advert.getAdvertsDate(),advert.getWayOfWorking(),advert.getAdvertsAbout(),advert.getAdvertsTitle(),advert.getAdvertsDescription()));
        }
        return new ResponseEntity<>(advertsDTOS, HttpStatus.OK);
    }

    @PostMapping("/adverts/create")
    public ResponseEntity<AdvertsDTO> create(@RequestBody AdvertsDTO request)
    {
        Optional<Company> tempCompany = companyRepository.findById(request.getCompanyId());
        Company company = tempCompany.get();
        Adverts advert = new Adverts(request.getCategoryId(), request.getCompanyId(), company.getCompanyName(), company.getAddress(), company.getCompanyUrl(), company.getCompanyAbout(), request.getAdvertsDate(),request.getWayOfWorking(),request.getAdvertsAbout(),request.getAdvertsTitle(),request.getAdvertsDescription());
        advert = advertsRepository.save(advert);
        return new ResponseEntity<>(new AdvertsDTO(advert.getId(), advert.getCategoryId(), advert.getCompanyId(), advert.getCompanyName(),advert.getCompanyLocation(),advert.getCompanyIcon(),advert.getCompanyDesc(),advert.getAdvertsDate(),advert.getWayOfWorking(),advert.getAdvertsAbout(),advert.getAdvertsTitle(),advert.getAdvertsDescription()),HttpStatus.OK);
    }

    @PostMapping("/adverts/check")
    public boolean checkApplication(@RequestBody CheckRequest request)
    {
        List<Application> applications = applicationRepository.findAll();
        for(Application application : applications )
        {
            if(application.getAdvertId()==request.advertid&& application.getApplicantId()==request.userid)
            {
                return true;
            }
        }

        return false;

    }

    @GetMapping("/adverts/{id}")
    public ResponseEntity<List<AdvertsDTO>> getAdvert(@PathVariable Long id)
    {
        Optional<Company> company1 = companyRepository.findById(id);
        if(company1.isPresent())
        {
            company = company1.get();
            List<Adverts> adverts = advertsRepository.findAll();
            List<AdvertsDTO> advertsDTOS = new ArrayList<>();
            for(Adverts advert : adverts)
            {
                if(advert.getCompanyId()==company.getId())
                {
                    advertsDTOS.add(new AdvertsDTO(advert.getId(), advert.getCategoryId(), advert.getCompanyId(), advert.getCompanyName(),advert.getCompanyLocation(),advert.getCompanyIcon(),advert.getCompanyDesc(),advert.getAdvertsDate(),advert.getWayOfWorking(),advert.getAdvertsAbout(),advert.getAdvertsTitle(),advert.getAdvertsDescription()));
                }
            }
            return new ResponseEntity<>(advertsDTOS, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}
