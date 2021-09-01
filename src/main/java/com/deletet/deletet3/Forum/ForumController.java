package com.deletet.deletet3.Forum;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class ForumController {

    private final ForumRepository forumRepository;

    public ForumController(ForumRepository forumRepository){
        this.forumRepository = forumRepository;
    }

    @GetMapping(value = "/forum/readAll")
    public ResponseEntity<List<ForumDTO>> readAll()
    {
        List<ForumHome> forum = forumRepository.findAll();
        List<ForumDTO> forumDTOS = new ArrayList<>();
        for(ForumHome forums : forum)
        {
            forumDTOS.add(new ForumDTO(forums.getId(), forums.getFullname(), forums.getTitle(), forums.getExplanation(), forums.getBody(), forums.getLanguages(), forums.getDate(), forums.getStatus(), forums.getLikecount(), forums.getUpcount(), forums.getImgUrl()));
        }
        return new ResponseEntity<>(forumDTOS, HttpStatus.OK);
    }

    @GetMapping("/forum/read/{id}")
    public ResponseEntity<ForumDTO> readForum(@PathVariable Long id)
    {
        Optional<ForumHome> optionalForumHome = forumRepository.findById(id);
        if (optionalForumHome.isPresent())
        {
            ForumHome db = optionalForumHome.get();
            db = forumRepository.save(db);
            return new ResponseEntity<>(new ForumDTO(db.getId(),db.getFullname(),db.getTitle(),db.getExplanation(),db.getBody(),db.getLanguages(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(), db.getImgUrl()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/forum/create")
    public ResponseEntity<ForumDTO> create(@RequestBody ForumDTO request)
    {
        LocalDateTime mydate = LocalDateTime.now();
        DateTimeFormatter myFormatdate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = mydate.format(myFormatdate);

        ForumHome forum = new ForumHome(request.getId(), request.getFullname(), request.getTitle(), request.getExplanation(), request.getBody(), request.getLanguages(), formattedDate, request.getStatus(), 0,0, request.getImgUrl());
        forum = forumRepository.save(forum);
        return new ResponseEntity<>(new ForumDTO(forum.getId(), forum.getFullname(), forum.getTitle(), forum.getExplanation(), forum.getBody(), forum.getLanguages(), forum.getDate(), forum.getStatus(), forum.getLikecount(), forum.getUpcount(),forum.getImgUrl()),HttpStatus.OK);
    }



    @PutMapping("/forum/updatelike/{id}")
    public ResponseEntity<ForumDTO> update(@PathVariable Long id)
    {
        Optional<ForumHome> optionalForumHome = forumRepository.findById(id);
        if (optionalForumHome.isPresent())
        {
            ForumHome db = optionalForumHome.get();
            db.setLikecount(db.getLikecount()+1);
            db = forumRepository.save(db);

            return new ResponseEntity<>(new ForumDTO(db.getId(),db.getFullname(),db.getTitle(),db.getExplanation(),db.getBody(),db.getLanguages(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(), db.getImgUrl()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/forum/deletelike/{id}")
    public ResponseEntity<ForumDTO> delete(@PathVariable Long id)
    {
        Optional<ForumHome> optionalForumHome = forumRepository.findById(id);
        if (optionalForumHome.isPresent())
        {
            ForumHome db = optionalForumHome.get();
            db.setLikecount(db.getLikecount()-1);
            db = forumRepository.save(db);

            return new ResponseEntity<>(new ForumDTO(db.getId(),db.getFullname(),db.getTitle(),db.getExplanation(),db.getBody(),db.getLanguages(),db.getDate(),db.getStatus(), db.getLikecount(), db.getUpcount(),db.getImgUrl()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/forum/updateup/{id}")
    public ResponseEntity<ForumDTO> update1(@PathVariable Long id)
    {
        Optional<ForumHome> optionalForumHome = forumRepository.findById(id);
        if (optionalForumHome.isPresent())
        {
            ForumHome db = optionalForumHome.get();
            db.setUpcount(db.getUpcount()+1);
            db = forumRepository.save(db);

            return new ResponseEntity<>(new ForumDTO(db.getId(),db.getFullname(),db.getTitle(),db.getExplanation(),db.getBody(),db.getLanguages(),db.getDate(),db.getStatus(), db.getLikecount(), db.getUpcount(),db.getImgUrl()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/forum/deleteup/{id}")
    public ResponseEntity<ForumDTO> delete1(@PathVariable Long id)
    {
        Optional<ForumHome> optionalForumHome = forumRepository.findById(id);
        if (optionalForumHome.isPresent())
        {
            ForumHome db = optionalForumHome.get();
            db.setUpcount(db.getUpcount()-1);
            db = forumRepository.save(db);

            return new ResponseEntity<>(new ForumDTO(db.getId(),db.getFullname(),db.getTitle(),db.getExplanation(),db.getBody(),db.getLanguages(),db.getDate(),db.getStatus(), db.getLikecount(), db.getUpcount(), db.getImgUrl()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("forum/delete/{id}")
    public ResponseEntity<Boolean> deleteforum(@PathVariable Long id)
    {
        Optional<ForumHome> optionalForumHome = forumRepository.findById(id);
        if(optionalForumHome.isPresent())
        {
            forumRepository.delete(optionalForumHome.get());
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

}

