package atomic;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void main(String[] args) {
        completedFutureExample();
    }

    static void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        System.out.println((cf.isDone()));
        System.out.println("message" == cf.getNow(null));
    }
}


