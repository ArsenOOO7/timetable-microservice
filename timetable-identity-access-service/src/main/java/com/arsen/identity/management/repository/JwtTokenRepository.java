package com.arsen.identity.management.repository;

import com.arsen.identity.management.domain.JwtToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends CrudRepository<JwtToken, Long> {

    JwtToken readByToken(String token);

}
