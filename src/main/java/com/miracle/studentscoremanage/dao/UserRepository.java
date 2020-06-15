package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    @Query(value = "SELECT name from user u where u.id = :id", nativeQuery = true)
    String findNameById(@Param("id") Long id);
}
