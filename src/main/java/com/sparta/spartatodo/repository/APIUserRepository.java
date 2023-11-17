package com.sparta.spartatodo.repository;

import com.sparta.spartatodo.domain.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIUserRepository extends JpaRepository<APIUser, String> {
}
