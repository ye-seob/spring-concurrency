package com.concurrency.springconcurrency;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private int capacity = 30;
    private int enrolled = 0;

    public void enroll() throws InterruptedException {
        Thread.sleep(50); // 스레드들이 동일한 시점에 임계 구간에 진입하도록 유도하기 위한 장치

        if (enrolled < capacity) {
            Thread.sleep(50);
            enrolled++;
        }
    }

    public int getEnrolledCount() {
        return enrolled;
    }
}