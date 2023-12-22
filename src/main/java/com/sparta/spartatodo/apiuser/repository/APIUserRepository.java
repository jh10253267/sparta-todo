package com.sparta.spartatodo.apiuser.repository;

import com.sparta.spartatodo.apiuser.domain.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface APIUserRepository extends JpaRepository<APIUser, String> {
    boolean existsAPIUserByMid(String username);

    Optional<APIUser> findByMid(String username);
}
