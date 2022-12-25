package se8.chap11thread;

import org.junit.jupiter.api.Test;

/**
 * @author: justin
 * @date: 2022/10/17
 */
public class WaitNotifyDemo {

    public static void main(String[] args){

    }

    @Test
    public void testNotifyWait(){
        Clerk clerk = new Clerk();
        Thread thread1 = new Thread(new Producer(clerk));
        Thread thread2 = new Thread(new Consumer(clerk));
        thread1.start();
        thread2.start();
    }

     class Clerk {
        private int product = -1; //競爭資源

        synchronized void setProduct(int product) throws InterruptedException {
            waitIfFull();
            this.product = product;
            System.out.printf("店員收到生產者的產品 %d%n", this.product);
            notify(); //通知等待集合中的執行緒(ex:消費者)
        }

        synchronized int getProduct() throws InterruptedException {
            waitIFEmpty();
            int product = this.product;
            this.product = -1;
            System.out.printf("消費者取走產品%d%n", product);
            notify(); //通知等待集合中的執行緒(ex:消費者)
            return product;
        }

        synchronized void waitIfFull() throws InterruptedException {
            while (this.product != -1){
                wait();
            }
        }

        synchronized void waitIFEmpty() throws InterruptedException {
            while(product == -1){
                wait();
            }
        }

    }

    class Producer implements Runnable {
        private Clerk clerk;

        public Producer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            System.out.println("生產者開始生產...");
            for(int i=1; i<=10; i++){
                try {
                    clerk.setProduct(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        private Clerk clerk;

        public Consumer(Clerk clerk){
            this.clerk = clerk;
        }

        @Override
        public void run() {
            System.out.println("消費者開始消費...");
            for(int i=1; i<=10; i++){
                try {
                    int product = clerk.getProduct();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
