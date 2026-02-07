package com.concurrency.springconcurrency;

import lombok.Synchronized;
import org.springframework.stereotype.Service;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class EnrollmentService {

    private int capacity = 30;
    private int enrolled = 0;
    private ReentrantLock lock = new ReentrantLock(true); //ReentrantLcok 객체 생성,  공정성 true(먼저 도착한 순으로 처리)

    @Synchronized
    public void enroll() throws InterruptedException {
        lock.lock(); // Lock
        Thread.sleep(50); // 스레드들이 동일한 시점에 임계 구간에 진입하도록 유도하기 위한 장치

        try {
            if (enrolled < capacity) {
                Thread.sleep(50);
                enrolled++;
            }
        } finally {
            lock.unlock();// 반드시 try-finally로 unlock
        }
    }

    public int getEnrolledCount() {
        return enrolled;
    }
}