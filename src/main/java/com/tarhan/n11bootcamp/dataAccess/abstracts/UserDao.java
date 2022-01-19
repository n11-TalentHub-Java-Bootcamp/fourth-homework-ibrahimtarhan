package com.tarhan.n11bootcamp.dataAccess.abstracts;

import com.tarhan.n11bootcamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
}
