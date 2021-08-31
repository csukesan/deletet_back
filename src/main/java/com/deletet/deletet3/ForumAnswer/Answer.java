package com.deletet.deletet3.ForumAnswer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity

public class Answer {

    @Id
    @SequenceGenerator(name="forum_squence",sequenceName= "forum_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "forum_sequence")
    private Long id;
    private Long userid;
    private String fullname;
    private String question_id;
    private String explanation;
    private String body;
    private String date;
    private String status;
    private int likecount;
    private int upcount;
    private boolean owner;

    public Answer(Long id, Long userid, String fullname, String question_id, String explanation, String body, String date, String status, int likecount, int upcount, boolean owner){

        this.id=id;
        this.userid=userid;
        this.fullname=fullname;
        this.question_id=question_id;
        this.explanation=explanation;
        this.body=body;
        this.date=date;
        this.status=status;
        this.likecount=likecount;
        this.upcount=upcount;
        this.owner=owner;
    }



}