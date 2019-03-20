package example.stop;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import example.Job;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class StopAfterDelay {

    public static void main(String[] args) {

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(a -> a == false)
                .withStopStrategy(StopStrategies.stopAfterDelay(15, TimeUnit.MILLISECONDS))
                .build();

            long start = System.currentTimeMillis();
        try {
            retryer.call(Job.JOB);
            long end = System.currentTimeMillis();
            System.out.println(end-start);
        } catch (RetryException e) {
            e.printStackTrace();
            long end = System.currentTimeMillis();
            System.out.println(end-start);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
