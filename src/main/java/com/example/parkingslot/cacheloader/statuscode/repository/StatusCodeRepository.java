package com.example.parkingslot.cacheloader.statuscode.repository;

import com.example.parkingslot.cacheloader.statuscode.entity.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "statusCodes")
public interface StatusCodeRepository extends JpaRepository<StatusCode,Integer> {
    Optional<StatusCode> findByCode(Integer code);
}
