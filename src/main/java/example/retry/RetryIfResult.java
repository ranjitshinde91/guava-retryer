package example.retry;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;

import java.util.concurrent.ExecutionException;

import static example.Job.JOB;

public class RetryIfResult {

    public static void main(String[] args) {

        RetryerBuilder<Boolean> retryerBuilder = RetryerBuilder.newBuilder();

        Retryer<Boolean> retryer = retryerBuilder
                .retryIfResult(a-> a == false)
                        .build();

        try {
            retryer.call(JOB);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }


    }
}
