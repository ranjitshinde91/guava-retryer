package example.timelimiter;

import com.github.rholder.retry.*;
import example.Job;
import example.listener.MyRetryListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.util.concurrent.SimpleTimeLimiter;

public class AttemptTimeLimiter {

    public static void main(String[] args) {

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(a -> a == false)
                .retryIfRuntimeException()
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(10, TimeUnit.MILLISECONDS))
                .withRetryListener(new MyRetryListener())
                .build();

        try {
            retryer.call(Job.JOB);
        } catch (RetryException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
