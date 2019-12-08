package com.cicekgamgam.news.service.news;


import java.util.List;

import lombok.Getter;

@Getter
class GetTopHeadlinesResponse {

    private String status;
    private Integer totalResults;
    private List<ArticleDto> articles;
}
