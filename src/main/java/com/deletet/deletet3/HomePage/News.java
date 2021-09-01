package com.deletet.deletet3.HomePage;


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
public class News {

    @Id
    @SequenceGenerator(name="news_squence",sequenceName= "news_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "news_sequence")
    private Long id;
    private String companyName;
    @Column(columnDefinition = "TEXT")
    private String companyIconUrl;
    @Column(columnDefinition = "TEXT")
    private String newsDescription;
    @Column(columnDefinition = "TEXT")
    private String newsImage;
    private int likeCount;
    private int commentCount;
    private String newsTag;

    public News(Long id, String companyName, String companyIconUrl, String newsDescription, String newsImage, int likeCount, int commentCount, String newsTag) {
        this.id = id;
        this.companyName = companyName;
        this.companyIconUrl = companyIconUrl;
        this.newsDescription = newsDescription;
        this.newsImage = newsImage;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.newsTag = newsTag;
    }
}
