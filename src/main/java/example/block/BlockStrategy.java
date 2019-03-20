package example.block;

import com.github.rholder.retry.*;
import example.Job;
import example.listener.MyRetryListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class BlockStrategy {

    public static void main(String[] args) {

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(a -> a == false)
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                .withRetryListener(new MyRetryListener())
                .withBlockStrategy(BlockStrategies.threadSleepStrategy())
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
