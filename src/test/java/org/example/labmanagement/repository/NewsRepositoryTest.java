package org.example.labmanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.News;
import org.example.labmanagement.dto.NewsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NewsRepositoryTest {
    @Autowired
    private NewsRepository newsRepository;

    @Test
    void save() {
        News news = News.builder()
                .title("908维修")
                .content("908要维修，预期维修6天")
                .author("麻子")
                .build();
        newsRepository.save(news);
    }

    @Test
    void fineAllNews() {

        for (NewsDTO n : newsRepository.findAllNews()) {
            log.debug(n.toString());
        }
    }

    @Test
    void delete() {
        News news =  newsRepository.findById("01JGAMVYHYGJB2Q4B4WQZG6JSV")
                .orElse(null);

    }
}