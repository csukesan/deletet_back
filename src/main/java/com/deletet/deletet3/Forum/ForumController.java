package com.deletet.deletet3.Forum;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;

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
            forumDTOS.add(new ForumDTO(forums.getId(), forums.getFullname(), forums.getTitle(), forums.getExplanation(), forums.getBody(), forums.getLanguages(), forums.getDate(), forums.getStatus(), forums.getLikecount(), forums.getUpcount(), forums.getImgUrl(), forums.getLikes()));
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
            return new ResponseEntity<>(new ForumDTO(db.getId(),db.getFullname(),db.getTitle(),db.getExplanation(),db.getBody(),db.getLanguages(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(), db.getImgUrl(), db.getLikes()), HttpStatus.OK);
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
        return new ResponseEntity<>(new ForumDTO(forum.getId(), forum.getFullname(), forum.getTitle(), forum.getExplanation(), forum.getBody(), forum.getLanguages(), forum.getDate(), forum.getStatus(), forum.getLikecount(), forum.getUpcount(),forum.getImgUrl(), forum.getLikes()),HttpStatus.OK);
    }

    @PutMapping("/forum/updatelike/{id}")
    public ResponseEntity<ForumDTO> update(@PathVariable Long id, @RequestBody ForumLikeRequest request)
    {
        boolean flag = false;
        Optional<ForumHome> optionalForumHome = forumRepository.findById(id);
        if (optionalForumHome.isPresent())
        {
            ForumHome db = optionalForumHome.get();
            String temp = db.getLikes();
            if(temp==null)
            {
                temp = request.getUserid();
                db.setLikecount(db.getLikecount()+1);
                db.setLikes(temp);
            }
            else
            {
                String[] likes = temp.split("\\W+");
                for(int i=0; i< likes.length;i++)
                {
                    if(likes[i].equals(request.getUserid()))
                    {
                        flag = true;
                    }
                }
                if(!flag)
                {
                    db.setLikecount(db.getLikecount()+1);
                    temp = temp + "," + request.getUserid();
                    db.setLikes(temp);
                }
            }
            db = forumRepository.save(db);

            return new ResponseEntity<>(new ForumDTO(db.getId(),db.getFullname(),db.getTitle(),db.getExplanation(),db.getBody(),db.getLanguages(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(), db.getImgUrl(), db.getLikes()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/forum/deletelike/{id}")
    public ResponseEntity<ForumDTO> delete(@PathVariable Long id, @RequestBody ForumLikeRequest request)
    {
        Optional<ForumHome> optionalForumHome = forumRepository.findById(id);
        if (optionalForumHome.isPresent())
        {

            ForumHome db = optionalForumHome.get();
            String temp = db.getLikes();
            String[] likes = temp.split("\\W+");
            System.out.println(likes.length);
            if(likes.length>1)
            {
                String[] anotherArray = new String[likes.length - 1];
                for(int i=0, k=0; i< likes.length;i++)
                {
                    if(likes[i].equals(request.getUserid()))
                    {
                        continue;
                    }
                    anotherArray[k++] = likes[i];
                }
                String delimiter =",";
                StringBuilder sb = new StringBuilder();
                for (String str : anotherArray)
                    sb.append(str).append(delimiter);
                temp = sb.substring(0, sb.length() - 1);
            }
            else
            {
                if(likes[0].equals(request.getUserid()))
                {
                    likes =null;
                    temp = null;
                }
            }

            db.setLikes(temp);
            db.setLikecount(db.getLikecount()-1);
            db = forumRepository.save(db);

            return new ResponseEntity<>(new ForumDTO(db.getId(),db.getFullname(),db.getTitle(),db.getExplanation(),db.getBody(),db.getLanguages(),db.getDate(),db.getStatus(), db.getLikecount(), db.getUpcount(),db.getImgUrl(),db.getLikes()), HttpStatus.OK);
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

