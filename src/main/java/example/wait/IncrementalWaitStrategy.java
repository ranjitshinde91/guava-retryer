package example.wait;

import com.github.rholder.retry.*;
import example.Job;
import example.listener.MyRetryListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class IncrementalWaitStrategy {

    public static void main(String[] args) {

        WaitStrategy incrementingWait = WaitStrategies.incrementingWait(1, TimeUnit.MICROSECONDS, 1 , TimeUnit.SECONDS);

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(a -> a == false)
                .withStopStrategy(StopStrategies.neverStop())
                .withWaitStrategy(incrementingWait)
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
