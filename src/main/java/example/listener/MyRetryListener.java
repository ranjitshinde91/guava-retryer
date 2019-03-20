package example.listener;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;

public class MyRetryListener implements RetryListener {

    @Override
    public <V> void onRetry(Attempt<V> attempt) {

        System.out.println(String.format("attempt %d Delay Since first Attempt %d millis, is success %b", attempt.getAttemptNumber(), attempt.getDelaySinceFirstAttempt(), attempt.hasResult()));

        if(attempt.hasResult()) {
            System.out.println("Result is :" + attempt.getResult());
        }

    }

}
