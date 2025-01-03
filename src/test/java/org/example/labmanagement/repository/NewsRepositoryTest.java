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
                .title("904维修")
                .content("904要维修，预期维修7天")
                .author("赵子")
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
        newsRepository.deleteById("01JGAMVYHYGJB2Q4B4WQZG6JSV");
        log.debug("删除成功");
    }
}