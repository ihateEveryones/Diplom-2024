package com.cb.repository;

import com.cb.model.Services;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Integer> {
    @Query("SELECT p FROM Services p WHERE p.delete=false ")
    List<Services> findAll();
    Optional<Services> findById(Integer id);


    @Query("SELECT p FROM Services p WHERE p.delete=false ")
    List<Services> findAllSorted(Sort sort);
}
