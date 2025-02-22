package com.cicekgamgam.news.service.news;

import lombok.Getter;

@Getter
public class ArticleDto {

    public ArticleDto(String description) {
        this.description = description;
    }

    private SourceDto source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
}
