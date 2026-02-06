package com.concurrency.springconcurrency.controller;

import com.concurrency.springconcurrency.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/enrollments")
@AllArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("enroll")
    private void enroll() throws InterruptedException {
        enrollmentService.enroll();
    }

    @GetMapping("/count")
    public Map<String, Object> getEnrollmentCount() {
        return Map.of(
                "lectureId", 1,
                "enrolled", enrollmentService.getEnrolledCount()
        );
    }

}