package com.deletet.deletet3.ForumAnswer;

import com.deletet.deletet3.Applications.Application;
import com.deletet.deletet3.Applications.ApplicationDTO;
import com.deletet.deletet3.Companies.Company;
import com.deletet.deletet3.Forum.ForumHome;
import com.deletet.deletet3.Forum.ForumRepository;
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
public class AnswerController {

    private final AnswerRepository answerRepository;
    private ForumHome forum;
    private ForumRepository forumRepository;

    public AnswerController(AnswerRepository answerRepository, ForumRepository forumRepository){
        this.answerRepository = answerRepository;
        this.forumRepository = forumRepository;
    }

    @GetMapping(value = "/answer/readAll")
    public ResponseEntity<List<AnswerDTO>> readAll()
    {
        List<Answer> answer = answerRepository.findAll();
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for(Answer answers : answer)
        {
            answerDTOS.add(new AnswerDTO(answers.getId(),answers.getUserid(), answers.getFullname(), answers.getQuestion_id(), answers.getExplanation(), answers.getBody(), answers.getDate(), answers.getStatus(),answers.getLikecount(),answers.getUpcount(),answers.isOwner(),answers.getImgUrl()));
        }
        return new ResponseEntity<>(answerDTOS, HttpStatus.OK);
    }

    @PostMapping("/answer/create")
    public ResponseEntity<AnswerDTO> create(@RequestBody AnswerDTO request)
    {
        LocalDateTime mydate = LocalDateTime.now();
        DateTimeFormatter myFormatdate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = mydate.format(myFormatdate);

        Answer answer = new Answer(request.getId(),request.getUserid(), request.getFullname(), request.getQuestion_id(), request.getExplanation(), request.getBody(), formattedDate, request.getStatus(),0,0, request.isOwner(), request.getImgUrl());
        answer = answerRepository.save(answer);
        return new ResponseEntity<>(new AnswerDTO(answer.getId(),answer.getUserid(), answer.getFullname(), answer.getQuestion_id(), answer.getExplanation(), answer.getBody(), answer.getDate(), answer.getStatus(),answer.getLikecount(),answer.getUpcount(),answer.isOwner(), answer.getImgUrl()),HttpStatus.OK);
    }

    @PutMapping("/answer/updatelike/{id}")
    public ResponseEntity<AnswerDTO> update(@PathVariable Long id)
    {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent())
        {
            Answer db = optionalAnswer.get();
            db.setLikecount(db.getLikecount()+1);
            db = answerRepository.save(db);

            return new ResponseEntity<>(new AnswerDTO(db.getId(),db.getUserid(),db.getFullname(),db.getQuestion_id(),db.getExplanation(),db.getBody(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(),db.isOwner(),db.getImgUrl()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/answer/deletelike/{id}")
    public ResponseEntity<AnswerDTO> delete(@PathVariable Long id)
    {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent())
        {
            Answer db = optionalAnswer.get();
            db.setLikecount(db.getLikecount()-1);
            db = answerRepository.save(db);

            return new ResponseEntity<>(new AnswerDTO(db.getId(),db.getUserid(),db.getFullname(),db.getQuestion_id(),db.getExplanation(),db.getBody(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(),db.isOwner(),db.getImgUrl()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/answer/updateup/{id}")
    public ResponseEntity<AnswerDTO> update1(@PathVariable Long id)
    {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent())
        {
            Answer db = optionalAnswer.get();
            db.setLikecount(db.getUpcount()+1);
            db = answerRepository.save(db);

            return new ResponseEntity<>(new AnswerDTO(db.getId(),db.getUserid(),db.getFullname(),db.getQuestion_id(),db.getExplanation(),db.getBody(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(),db.isOwner(), db.getImgUrl()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/answer/deleteup/{id}")
    public ResponseEntity<AnswerDTO> delete1(@PathVariable Long id)
    {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent())
        {
            Answer db = optionalAnswer.get();
            db.setLikecount(db.getUpcount()-1);
            db = answerRepository.save(db);

            return new ResponseEntity<>(new AnswerDTO(db.getId(),db.getUserid(),db.getFullname(),db.getQuestion_id(),db.getExplanation(),db.getBody(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(),db.isOwner(), db.getImgUrl()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("answer/delete/{id}")
    public ResponseEntity<Boolean> deleteanswer(@PathVariable Long id)
    {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent())
        {
            answerRepository.delete(optionalAnswer.get());
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

    @GetMapping("answer/read/{id}")
    public ResponseEntity<List<AnswerDTO>> readAnswer(@PathVariable Long id)
    {
        Optional<ForumHome> forumHome = forumRepository.findById(id);
        if(forumHome.isPresent())
        {
            forum = forumHome.get();
            List<Answer> answers = answerRepository.findAll();
            List<AnswerDTO> answerDTOS = new ArrayList<>();
            for(Answer db : answers)
            {
                if(db.getQuestion_id().equals(id))
                {
                    answerDTOS.add(new AnswerDTO(db.getId(),db.getUserid(),db.getFullname(),db.getQuestion_id(),db.getExplanation(),db.getBody(),db.getDate(),db.getStatus(),db.getLikecount(), db.getUpcount(),db.isOwner(), db.getImgUrl()));
                }
            }
            return new ResponseEntity<>(answerDTOS, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }


}
