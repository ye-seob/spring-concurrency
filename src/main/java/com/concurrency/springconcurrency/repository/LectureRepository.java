package com.concurrency.springconcurrency.repository;

import com.concurrency.springconcurrency.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
}
