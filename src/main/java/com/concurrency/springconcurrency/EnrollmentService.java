package com.concurrency.springconcurrency;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private int capacity = 30;
    private int enrolled = 0;

    public void enroll() {
        if (enrolled < capacity) {
            try {
                Thread.sleep(1); // 스레드들이 동일한 시점에 임계 구간에 진입하도록 유도하기 위한 장치
            }  catch (InterruptedException e) {
            }

            enrolled++;
        }
    }

    public int getEnrolledCount() {
        return enrolled;
    }
}