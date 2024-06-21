package com.archi.sample.repository;

import com.archi.sample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE email=?", nativeQuery = true)
    public User findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user WHERE email=:email", nativeQuery = true)
    public void deleteByEmail(String email);
}
