package com.deletet.deletet3.ForumAnswer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/deletet")
@RestController
public class AnswerController {

    private final AnswerRepository answerRepository;

    public AnswerController(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    @GetMapping(value = "/answer/readAll")
    public ResponseEntity<List<AnswerDTO>> readAll()
    {
        List<Answer> answer = answerRepository.findAll();
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for(Answer answers : answer)
        {
            answerDTOS.add(new AnswerDTO(answers.getId(), answers.getFullname(), answers.getQuestion_id(), answers.getExplanation(), answers.getBody(), answers.getDate(), answers.getStatus()));
        }
        return new ResponseEntity<>(answerDTOS, HttpStatus.OK);
    }

    @PostMapping("/answer/create")
    public ResponseEntity<AnswerDTO> create(@RequestBody AnswerDTO request)
    {
        LocalDateTime mydate = LocalDateTime.now();
        DateTimeFormatter myFormatdate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = mydate.format(myFormatdate);

        Answer answer = new Answer(request.getId(), request.getFullname(), request.getQuestion_id(), request.getExplanation(), request.getBody(), formattedDate, request.getStatus());
        answer = answerRepository.save(answer);
        return new ResponseEntity<>(new AnswerDTO(answer.getId(), answer.getFullname(), answer.getQuestion_id(), answer.getExplanation(), answer.getBody(), answer.getDate(), answer.getStatus()),HttpStatus.OK);
    }

}
