package se8.chap11thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: justin
 * @date: 2022/10/17
 */


public class DeadLockDemo {

    /***
     * 兩個thread 各自持有對方需要的鎖, 互相都進入blocked狀態,
     * 就進入死結
     * @throws InterruptedException
     */
    @Test
    public void deadLockTest() throws InterruptedException {
        Resource resource1 = new Resource("res1", 1);
        Resource resource2 = new Resource("res2", 1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread1 = new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0; i <10; i++){
                resource1.cooperate(resource2);
            }
        });

        Thread thread2 = new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0; i<10; i++){
               resource2.cooperate(resource1);
           }
        });

        thread1.start();
        thread2.start();
        //Thread.sleep(5000);
        countDownLatch.countDown();
    }

    class Resource {
        private String name;
        private int resource;

        Resource(String name, int resource){
            this.name = name;
            this.resource = resource;
        }

        String getName(){
            return this.name;
        }

        synchronized int addResource(){
            return resource++;
        }

        synchronized void cooperate(Resource resource){
            resource.addResource();
            System.out.printf("%s 整合 %s 的資源", this.getName(), resource.getName());
        }
    }

    class NoDeadLockResource {
        ReentrantLock lock = new ReentrantLock();

        private String name;
        private int resource;

        NoDeadLockResource(String name, int resource){
            this.name = name;
            this.resource = resource;
        }

        String getName(){
            return this.name;
        }


        void cooperate(NoDeadLockResource resource){
            while(true){
                try
                {
                    if (lockMeAnd(resource)){
                        System.out.printf("%s 整合 %s 的資源", this.getName(), resource.getName());
                        break;
                    }
                }finally {
                    unlockMeAnd(resource);
                }
            }

        }

        private boolean lockMeAnd(NoDeadLockResource res){
            //tryLock失敗不會阻斷, 只會傳回false.
            return lock.tryLock() && res.lock.tryLock();
        }

        private void unlockMeAnd(NoDeadLockResource res){
            if (lock.isHeldByCurrentThread())
                lock.unlock();

            if (res.lock.isHeldByCurrentThread())
                res.lock.unlock();
        }
    }
}
