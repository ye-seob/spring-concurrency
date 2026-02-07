package com.concurrency.springconcurrency.service;

import com.concurrency.springconcurrency.domain.Lecture;
import com.concurrency.springconcurrency.repository.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    private static final Long TEST_LECTURE_ID = 1L;

    private final LectureRepository lectureRepository;

    public EnrollmentService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public void enroll() throws InterruptedException {

        // 동시성 테스트용 지연
        Thread.sleep(50);

        Lecture lecture = lectureRepository.findByIdForUpdate(TEST_LECTURE_ID)
                .orElseThrow(() -> new IllegalStateException("강의가 존재하지 않습니다"));

        // 동시성 테스트용 지연
        Thread.sleep(50);

        if(lecture.getEnrolled() < lecture.getCapacity()){
            lecture.enroll();
        }
    }


    @Transactional(readOnly = true)
    public int getEnrolledCount() {
        Lecture lecture = lectureRepository.findById(TEST_LECTURE_ID)
                .orElseThrow(() -> new IllegalStateException("강의가 존재하지 않습니다."));

        return lecture.getEnrolled();
    }
}