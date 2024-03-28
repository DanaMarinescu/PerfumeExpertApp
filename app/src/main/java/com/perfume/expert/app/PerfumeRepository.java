package com.perfume.expert.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<PerfumeModel, Long> {
    List<PerfumeModel> findAll();
}