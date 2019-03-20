package example.wait;

import com.github.rholder.retry.*;
import example.Job;
import example.listener.MyRetryListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class JoinWaitStrategy {

    public static void main(String[] args) {

        WaitStrategy fixedWait = WaitStrategies.fixedWait(2, TimeUnit.SECONDS);
        WaitStrategy fixedWait2 = WaitStrategies.fixedWait(1, TimeUnit.SECONDS);

        WaitStrategy waitStrategy = WaitStrategies.join(fixedWait, fixedWait2);


        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(a -> a == false)
                .withStopStrategy(StopStrategies.neverStop())
                .withWaitStrategy(waitStrategy)
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
