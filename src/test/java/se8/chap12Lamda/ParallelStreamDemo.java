package se8.chap12Lamda;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * @author: justin
 * @date: 2022/10/22
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParallelStreamDemo {

    List<Integer> list = null;

    @BeforeAll
    public void initBean() {
        list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void forEachTest() {
        list.parallelStream()
                .forEachOrdered(out::println);
    }

    /***
     * 經過實測, 這邊使用parallelStream平行處理的結果反而比使用stream還慢
     * parallelStream: 13~16毫秒
     * stream:1毫秒
     *
     * 推測應該是因為: 當計算不夠複雜時, 且資料量不夠多, 使用平行處理所消耗的成本反而比循序處理來得高.
     *
     */
    @Test
    public void oneTimeOneThingTest() {
        long startTime = System.currentTimeMillis();
        List<Integer> biggerThan5 = list.parallelStream()
                .filter(num -> num > 5)
                .collect(Collectors.toList());

        biggerThan5.forEach(out::println);

        List<Integer> newNums = biggerThan5.parallelStream().map(num->num+10).collect(Collectors.toList());
        newNums.forEach(out::println);
        long endTime = System.currentTimeMillis();
        long cost = (endTime - startTime);
        System.out.printf("共花費 %d 豪秒 %n", cost);
    }

    @Test
    public void wrongStyleParallelStreamTest(){
         long startTime = System.currentTimeMillis();


        List<Integer> newNums = new ArrayList<>();
        list.stream().filter(num->{
            boolean isBiggerThan5 = num > 5;
            if (isBiggerThan5){
                newNums.add(num+10);
            }
            return isBiggerThan5;
        }).forEachOrdered(out::println);
        newNums.forEach(out::println);
        long endTime = System.currentTimeMillis();
        long cost = (endTime - startTime);
        System.out.printf("共花費 %d 豪秒 %n", cost);
    }
}
