package com.deletet.deletet3.Adverts;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;


@CrossOrigin(origins = "", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class AdvertsController {

    private final AdvertsRepository advertsRepository;

    public AdvertsController(AdvertsRepository advertsRepository){
        this.advertsRepository = advertsRepository;
    }

    @GetMapping(value = "/adverts/readAll")
    public ResponseEntity<List<AdvertsDTO>> readAll()
    {
        List<Adverts> adverts = advertsRepository.findAll();
        List<AdvertsDTO> advertsDTOS = new ArrayList<>();
        for(Adverts advert : adverts)
        {
            advertsDTOS.add(new AdvertsDTO(advert.getId(),advert.getCategoryId(),advert.getCompanyName(),advert.getCompanyLocation(), advert.getCompanyIcon(), advert.getCompanyDesc(), advert.getAdvertsDate(),advert.getWayOfWorking(),advert.getAdvertsAbout(),advert.getAdvertsTitle(),advert.getAdvertsDescription()));
        }
        return new ResponseEntity<>(advertsDTOS, HttpStatus.OK);
    }

    @PostMapping("/adverts/create")
    public ResponseEntity<AdvertsDTO> create(@RequestBody AdvertsDTO request)
    {
        Adverts advert = new Adverts(request.getId(), request.getCategoryId(), request.getCompanyName(), request.getCompanyLocation(), request.getCompanyIcon(), request.getCompanyDesc(), request.getAdvertsDate(),request.getWayOfWorking(),request.getAdvertsAbout(),request.getAdvertsTitle(),request.getAdvertsDescription());
        advert = advertsRepository.save(advert);
        return new ResponseEntity<>(new AdvertsDTO(advert.getId(), advert.getCategoryId(),advert.getCompanyName(),advert.getCompanyLocation(),advert.getCompanyIcon(),advert.getCompanyDesc(),advert.getAdvertsDate(),advert.getWayOfWorking(),advert.getAdvertsAbout(),advert.getAdvertsTitle(),advert.getAdvertsDescription()),HttpStatus.OK);
    }

}
