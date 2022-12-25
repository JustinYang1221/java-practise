package se8.chap12Lamda;

import org.junit.jupiter.api.Test;
import org.xmlunit.builder.Input;

import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: justin
 * @date: 2022/12/25
 */
public class CompetableFutureTest {

    public static CompletableFuture<String> uploadFileAsync(InputStream inputStream, ExecutorService executorService) throws InterruptedException {
        // 第一個參數: 指定的supplier, 即要非同步處理的工作內容, 第二個參數: Executor
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 開始上傳圖檔");
            Random random = new Random();
            int sleepTime = random.nextInt(3) + 1;
            String id = String.valueOf(Thread.currentThread().getName());
            try {
                System.out.println(Thread.currentThread().getName() + "睡眠" + sleepTime + "秒");
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            return id;
        }, executorService);
    }

    public static CompletableFuture<InputStream> compactInputStream(InputStream inputStream, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            InputStream inputStream1 =null;
            try {

                File file = new File("/Users/justing/cutPicture/selector.png");
                  inputStream1 = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return inputStream1;
        }, executorService);

    }

    @Test
    public void testUploadImageAndWriteUrlToDbTest() throws InterruptedException {
        List<String> errors = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long begin = System.currentTimeMillis();
        File[] files = new File[5];
        List<CompletableFuture<String>> completableFutureList = Arrays.stream(files).map(file -> {
            CompletableFuture<String> completableFuture = null;
            completableFuture = compactInputStream(null, executorService)
                    .thenCompose(inputStream -> { //回傳的必須是一個 CompletionStage<String>
                        try {
                            return uploadFileAsync(inputStream, executorService);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).thenApply(imageUrl -> {
                        Random random = new Random();
                        int i = random.nextInt(3);
                        try {
                            if (i == 0)
                                throw new IOException(imageUrl + " 寫入db突然發生錯誤");

                            System.out.println("開始將imageUrl'" + imageUrl + "'寫入db");

                            TimeUnit.SECONDS.sleep(1L);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            throw new CompletionException(e);
                        }
                        return imageUrl;
                    }).whenComplete((ok, error) -> {
                        if (error != null) {
                            error.printStackTrace();
                        } else
                            System.out.println(String.format("ImageUrl: %s 完成上傳、寫入db", ok));
                    });
            return completableFuture;
        }).collect(Collectors.toList());
        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[]{})).join();
        System.out.println("最终耗时" + (System.currentTimeMillis() - begin) + "毫秒");
        executorService.shutdown();

//                    .exceptionally(error->{
//                        System.out.println(error.getMessage());
//                        return "";
//                    });


    }

    public static void main(String[] args) throws Exception {

        long begin = System.currentTimeMillis();
        // 自定义一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 循环创建10个CompletableFuture
        List<CompletableFuture<Integer>> collect = IntStream.range(1, 10).mapToObj(i -> {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                        // 在i=5的时候抛出一个NPE
                        if (i == 5) {
                            throw new NullPointerException();
                        }
                        try {
                            // 每个依次睡眠1-9s，模拟线程耗时
                            TimeUnit.SECONDS.sleep(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(i);
                        return i;
                    }, executorService)
                    // 这里处理一下i=5时出现的NPE
                    // 如果这里不处理异常，那么异常会在所有任务完成后抛出,小伙伴可自行测试
                    .exceptionally(Error -> {
                        try {
                            TimeUnit.SECONDS.sleep(5);
                            System.out.println("錯誤發生了, 先錯誤處理");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return 100;
                    });
            return future;
        }).collect(Collectors.toList());
        // List列表转成CompletableFuture的Array数组,使其可以作为allOf()的参数
        // 使用join()方法使得主线程阻塞，并等待所有并行线程完成
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{})).join();
        System.out.println("最终耗时" + (System.currentTimeMillis() - begin) + "毫秒");
        executorService.shutdown();
//        ExecutorService poolService = Executors.newFixedThreadPool(10);
//        IntStream.range(1, 5).forEach(id->{
//            try {
//                uploadFileAsync(null, poolService);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

    }

    private void writeDb() throws Throwable {

    }


}
