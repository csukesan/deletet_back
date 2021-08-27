package com.deletet.deletet3.Forum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity

public class ForumHome {

    @Id
    @SequenceGenerator(name="forum_squence",sequenceName= "forum_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "forum_sequence")

    private Long id;
    private String fullname;
    private String title;
    private String explanation;
    private String body;
    private String languages;
    private String date;
    private String status;
    private int likecount;
    private int upcount;

public ForumHome(Long id, String fullname, String title, String explanation, String body, String languages, String date, String status, int likecount, int upcount){

    this.id=id;
    this.fullname=fullname;
    this.title=title;
    this.explanation=explanation;
    this.body=body;
    this.languages=languages;
    this.date=date;
    this.status=status;
    this.likecount=likecount;
    this.upcount=upcount;
}



}
