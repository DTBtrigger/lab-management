package org.example.labmanagement.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query("""
        select * from user u where u.account=:account 
""")
    User findUserByAccount(String account);

    @Query("""
        select * from user u
""")
    List<User> findAllUser();

    @Modifying
    @Query("""
        update user set password=:newPassword where id=:userId
""")
    void updatePasswordByUserId(String userId, String newPassword);

}
