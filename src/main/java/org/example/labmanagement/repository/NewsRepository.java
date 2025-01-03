package org.example.labmanagement.repository;

import org.example.labmanagement.dox.News;
import org.example.labmanagement.dto.NewsDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News,String> {
    @Query("""
    select id,title,author,content,update_time from news
""")
    List<NewsDTO> findAllNews();


}
