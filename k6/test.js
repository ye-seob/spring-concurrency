import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1s', target: 100 },
    { duration: '2s', target: 100 },
    { duration: '1s', target: 0 },
  ],
  thresholds: {
    http_req_failed: ['rate<0.01'],
    http_req_duration: ['p(95)<1000'],
  },
};

export default function () {
  const res = http.post(
    'http://localhost:8081/enrollments/enroll',
    null,
    {
      headers: {
        'Content-Type': 'application/json',
      },
    }
  );

  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}