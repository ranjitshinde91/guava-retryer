package example.wait;

import com.github.rholder.retry.*;
import example.Job;
import example.listener.MyRetryListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RandomWaitStrategy {

    public static void main(String[] args) {

        WaitStrategy randomWait = WaitStrategies.randomWait(1, TimeUnit.SECONDS, 2, TimeUnit.SECONDS);

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(a -> a == false)
                .withStopStrategy(StopStrategies.neverStop())
                .withWaitStrategy(randomWait)
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
