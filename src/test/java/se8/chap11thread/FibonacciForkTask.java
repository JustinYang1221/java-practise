package se8.chap11thread;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author: justin
 * @date: 2022/10/19
 */
public class FibonacciForkTask extends RecursiveTask<Long> {
    final long n;

    FibonacciForkTask(Long n){
        this.n = n;
    }

    @Override
    protected Long compute() {
//        if (n <= 20){
//            return solveSequencial(n);
//        }
        if (n<=1)
            return n;

        System.out.printf("execute Fibonacci(%d).fork %n", n-1);
        //執行 ForkJoinTask的是 ForkJoinWorkerThread, 每個thread都有自己的workQueue, fork就是將
        //一個新的子任務add至該ForkJoinWorkerThread的workQueue中, 等待
        //ForkJoinPool分配執行緒來執行該子任務
        ForkJoinTask<Long> subTask = new FibonacciForkTask(n-1).fork();
        return new FibonacciForkTask(n-2).compute() + subTask.join();
    }

    long solveSequencial(long n){
        if (n<=1)
            return n;

        return solveSequencial(n-1)+ solveSequencial(n-2);
    }
}
