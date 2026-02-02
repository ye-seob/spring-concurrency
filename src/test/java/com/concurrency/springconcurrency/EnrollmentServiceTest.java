package com.concurrency.springconcurrency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
/*

메인 스레드
    |
    |---> 스레드풀에 100개의 enroll()
    |       최대 10개 스레드가 동시에 실행
    |
    |---> latch.await() : 모든 작업 끝날 때까지 대기
    |
    v

 */
@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Test
    void 동시에_수강신청하면_정원을_초과할_수있다() throws InterruptedException {
        // given
        EnrollmentService enrollmentService = new EnrollmentService();
        int threadCount = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    enrollmentService.enroll();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // then
        int enrolled = enrollmentService.getEnrolledCount();
        System.out.println(enrolled);

        assertThat(enrolled).isLessThanOrEqualTo(30);
    }


}