package example;

import example.exception.MemberNotFoundException;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Job {

    public static final Callable<Boolean> JOB = () -> {
        int number = ThreadLocalRandom.current().nextInt(7);
        System.out.println(String.format("job number %d", number));
        System.out.println("Thread "+Thread.currentThread().getId());
        if (number == 6) {
            return true;
        }
        return false;
    };


    public static final Callable<String> MEMBER_SEARCH = () -> {
        int number = ThreadLocalRandom.current().nextInt(7);
        System.out.println(String.format("job number %d", number));
        if (number == 6) {
            return "Ranjit";
        }
        throw new MemberNotFoundException("Member Not Found");
    };
}
