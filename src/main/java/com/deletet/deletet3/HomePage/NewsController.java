package com.deletet.deletet3.HomePage;


import com.deletet.deletet3.Adverts.Adverts;
import com.deletet.deletet3.Adverts.AdvertsDTO;
import com.deletet.deletet3.Adverts.AdvertsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class NewsController {

    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    @GetMapping(value = "/homepage")
    public ResponseEntity<List<NewsDTO>> readAll()
    {
        List<News> news = newsRepository.findAll();
        List<NewsDTO> newsDTOS = new ArrayList<>();
        for(News news1 : news)
        {
            newsDTOS.add(new NewsDTO(news1.getId(), news1.getCompanyName(), news1.getCompanyIconUrl(), news1.getNewsDescription(), news1.getNewsImage(), news1.getLikeCount(), news1.getCommentCount(), news1.getNewsTag()));
        }
        return new ResponseEntity<>(newsDTOS, HttpStatus.OK);
    }
    @PostMapping("/homepage/add")
    public ResponseEntity<NewsDTO> create(@RequestBody NewsDTO request)
    {
        News news = new News(request.getId(), request.getCompanyName(), request.getCompanyIconUrl(), request.getNewsDescription(), request.getNewsImage(), request.getLikeCount(), request.getCommentCount(),request.getNewsTag());
        news = newsRepository.save(news);
        return new ResponseEntity<>(new NewsDTO(news.getId(), news.getCompanyName(), news.getCompanyIconUrl(), news.getNewsDescription(), news.getNewsImage(), news.getLikeCount(), news.getCommentCount(),news.getNewsTag()),HttpStatus.OK);
    }

}
