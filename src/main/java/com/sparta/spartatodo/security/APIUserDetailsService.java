package com.sparta.spartatodo.security;

import com.sparta.spartatodo.domain.APIUser;
import com.sparta.spartatodo.dto.APIUserDTO;
import com.sparta.spartatodo.repository.APIUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class APIUserDetailsService implements UserDetailsService {
    private final APIUserRepository apiUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<APIUser> result = apiUserRepository.findById(username);
        APIUser apiUser = result.orElseThrow(() -> new UsernameNotFoundException("Cannot find User"));

        APIUserDTO dto = new APIUserDTO(
                apiUser.getMid(),
                apiUser.getMpw(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        log.info(dto);

        return dto;

    }
}
