package se8.chap12Lamda;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

public class CompletableFutureDemo {


    /***
     * 應用 CompletableFuture來達成非同步的檔案讀取
     * @param file
     * @param service
     * @return
     */
    public static CompletableFuture<String> readFileAsync(String file, ExecutorService service) {
        // 第一個參數: 指定的supplier, 即要非同步處理的工作內容, 第二個參數: Executor
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new String(Files.readAllBytes(Paths.get(file)));
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }, service);
    }

    public static CompletableFuture<String> processContentAsync(String content, ExecutorService service) {
        return CompletableFuture.supplyAsync(()->{
            return content.toLowerCase();
        }, service);

    }

    public static CompletableFuture<String> uploadFileAsync(InputStream inputStream, ExecutorService executorService){
        return null;
    }


    public static void main(String[] args) throws Exception {
        String home = System.getProperty("user.home"); // /Users/justing
        String gitPath = File.separator + "github" + File.separator + "testFile"+ File.separator;
        String filePath = home + gitPath; //+ "StringDemo.java";
        args[0] = filePath + args[0];

        ExecutorService poolService = Executors.newFixedThreadPool(10);

        //whenComplete第一個參數是傳入 supplyAsync執行的supplier的傳回值
        //第二個參數是當在supplyAsync靜態方法執行錯誤回傳的例外實例
        readFileAsync(args[0], poolService)
                .thenApplyAsync(String::toUpperCase) //再次非同步的執行處理結果
                .thenCompose(content-> processContentAsync(content, poolService))
                .whenComplete((ok, ex) -> {
            if(ex == null) {
                out.println(ok);
            } else {
                ex.printStackTrace();
            }
        }).join(); //join是為了避免main執行緒在任務完成前就關閉ExecutorService

        poolService.shutdown();


    }
}
