package example.stop;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import example.Job;

import java.util.concurrent.ExecutionException;

public class StopAfterAttempt {

    public static void main(String[] args) {

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(a -> a == false)
                .withStopStrategy(StopStrategies.stopAfterAttempt(2))
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
