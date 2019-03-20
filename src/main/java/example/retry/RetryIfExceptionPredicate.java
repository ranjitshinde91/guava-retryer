package example.retry;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;

import java.util.concurrent.ExecutionException;

import static example.Job.MEMBER_SEARCH;

public class RetryIfExceptionPredicate {

    public static void main(String[] args) {

        RetryerBuilder<String> retryerBuilder = RetryerBuilder.newBuilder();

        Retryer<String> retryer = retryerBuilder
                .retryIfException(exception -> exception.getMessage().equals("Member Not Found"))
                .retryIfExceptionOfType(RuntimeException.class)
                .build();

        try {
            retryer.call(MEMBER_SEARCH);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }

    }
}
