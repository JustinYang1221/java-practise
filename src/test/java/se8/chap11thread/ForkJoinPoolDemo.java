package se8.chap11thread;

import java.util.concurrent.ForkJoinPool;

/**
 * @author: justin
 * @date: 2022/10/18
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args){
        FibonacciForkTask fibonacciForkTask = new FibonacciForkTask(8L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long result = forkJoinPool.invoke(fibonacciForkTask);
        System.out.println("result="+ result);

    }




}
