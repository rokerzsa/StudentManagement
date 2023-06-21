package com.project.teststudent.studentmanger.repositories;

import com.project.teststudent.studentmanger.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
