package se8.chap11thread;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

/**
 * @author: justin
 * @date: 2022/10/18
 */
public class ScheduledExecutorServiceDemo {




   public static void main(String[] args) throws Exception{
       ScheduledExecutorService service =
               Executors.newSingleThreadScheduledExecutor();
               Executors.newScheduledThreadPool(2);

       out.printf("before schedule Date:%s%n", new Date());
       //initialDelay只影響第一次開始執行前要delay多久
       //當只有一個runable再跑時, 是如下的描述
       //實際執行時間+ schedule設定的delay time= 每次執行間隔時間
       //以這個例子來看, 執行兩秒, delay 1秒, 所以每次執行間隔是3秒
       service.scheduleWithFixedDelay(() -> {
           out.println(Thread.currentThread().getName() + ":" + new Date());
           try{
               Thread.sleep(2000);
           }catch (Exception e){

           }
       }, 1000 , 1000, TimeUnit.MILLISECONDS);

       //當增加一個runnable, 而ExecutorService的pool又只有一個thread可用時
       //上面設定的 delay就失效了.
       service.scheduleWithFixedDelay(() -> {
           out.println(Thread.currentThread().getName() + ":" + new Date());
           try{
               Thread.sleep(1000);
           }catch (Exception e){

           }
       }, 1000 , 1000, TimeUnit.MILLISECONDS);
   }

    /***
     * 固定週期, 就執行runable, 只要runable的執行
     * 小於等於 peroid 參數, 則執行的間隔始終為 peroid
     * 當執行時間大於peroid, 則前一次執行完, 立刻執行下一次
     */
   @Test
   public void scheduleAtFixedRateTest(){
       ScheduledExecutorService service =
               Executors.newScheduledThreadPool(5);

       service.scheduleAtFixedRate(()->{
           out.println(new Date());
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }, 0, 2000, TimeUnit.MILLISECONDS);

       while(true){

       }
   }

}
