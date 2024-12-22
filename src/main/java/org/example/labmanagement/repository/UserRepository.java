package org.example.labmanagement.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query("""
        select * from user u where u.account=:account 
""")
    User findUserByAccount(String account);
}
