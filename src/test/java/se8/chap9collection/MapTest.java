package se8.chap9collection;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.*;

/**
 * @author: justin
 * @date: 2022/10/10
 */
public class MapTest {

    @Test
    public void secureRandomTest(){
        SecureRandom sr = new SecureRandom();
        float nextFloat = sr.nextFloat();
        System.out.println("secure random:"+ nextFloat);
        double randValue = sr.nextFloat() * 100;
        System.out.println(randValue);
        System.out.println(Math.ceil(randValue));
    }



    /***
     * 印出金字塔形狀練習
     * 例如輸入 5, 就印出5層金字塔
     *      *
     *     ***
     *    *****
     *   *******
     *  *********
     */
    @Test
    public void printGoldenTa(){
        int level = 6;

        //控制層數
        for(int i=1; i<= level; i++){
            //print right empty
            for(int j=i; j< level; j++ ){
                System.out.print(" ");
            }

            //print start
            int startLen= 1+ 2*(i-1);
            int l = 0;
            while(l < startLen) {
                System.out.print("*");
                l++;
            }

            //print left empth
            for(int j=i; j< level; j++ ){
                System.out.print(" ");
            }
            //換層
            System.out.println("");
        }

    }

    public static void main(String[] args){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "t1");
        hashMap.put("1", "t2");
        hashMap.forEach((k,v)->{
            System.out.println(String.format("key=%s, value=%s", k,v));
        });

        //用lamda寫法直接實作comparator的compare方法, 讓treeMap排序以
        //遞減排序方式
        Map<String, String> treeMap = new TreeMap<>((s1,s2)->{
            return -s1.compareTo(s2);
        });

        treeMap.put("Action", "開始行動");
        treeMap.put("Bob", "最慢");
        treeMap.put("Cat", "可愛");
        System.out.println(treeMap);

        Map<Integer, String> treeMap2 = new TreeMap<>();
        treeMap2.put(3, "333");
        treeMap2.put(2, "333");
        treeMap2.put(1, "333");

        MapTest.foreach(treeMap.keySet());
        MapTest.foreach(treeMap2.keySet());
    }

    /***
     * 只要是繼承Iterable的類別都可以使用forEach(), 或是增強式迴圈
     * p9-40
     * @param iterable
     * @param <T>
     */
    public static <T> void foreach(Iterable<T> iterable){
        //增強式for迴圈語法
        for(T element: iterable){
            System.out.println(element);
        }
    }
}
