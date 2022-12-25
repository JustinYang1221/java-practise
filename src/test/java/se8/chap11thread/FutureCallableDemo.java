package se8.chap11thread;

import java.util.concurrent.*;

import static java.lang.System.*;
/**
 * @author: justin
 * @date: 2022/10/18
 */
public class FutureCallableDemo {
    static long fibonacci(long n){
        if (n<=1)
            return n;

        return fibonacci(n-1)+ fibonacci(n-2);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return fibonacci(30);
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Long> future = executorService.submit(callable);
        out.println("ExecutorService submit callable 版本");
        while (!future.isDone()){
            out.println("還沒好喔, 等一會再過來拿");
        }
        out.printf("第30個費式數為%d%n", future.get());
        out.println("===============================");

        out.println("一般Thread送出版本");
        // FutureTask 在建構時, 一般是傳入可回傳值的Callable介面的實例
        FutureTask<Long> the30thFibFuture =
                new FutureTask<Long>(()-> fibonacci(30));

        out.println("我要第30個費式數, 待會來拿...");

        new Thread(the30thFibFuture).start();
        while(!the30thFibFuture.isDone()){
            out.println("先處理別的事, 等等再過來看好了沒");
        }
        out.printf("第30個費式數為%d%n", the30thFibFuture.get());

    }
}
