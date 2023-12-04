package com.sparta.spartatodo.apiuser.repository;

import com.sparta.spartatodo.apiuser.domain.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIUserRepository extends JpaRepository<APIUser, String> {
}
