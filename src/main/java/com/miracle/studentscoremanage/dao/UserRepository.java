package com.miracle.studentscoremanage.dao;

import com.miracle.studentscoremanage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    String findNameById(Long id);
}
