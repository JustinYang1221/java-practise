package se8.chap9collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.reverseOrder;

/**
 * @author: justin
 * @date: 2022/10/9
 */
public class SortTest {

    public static void main(String[] args){
        String a = "111";
        String b = "123";
        System.out.println(a.compareTo(b));

        List words = Arrays.asList("X", "A", "C", "Z", null, "G", null);
        words.sort(Comparator.nullsFirst(reverseOrder()));

        System.out.println(words);

    }

    @Test
    public void ArrayCopyTest(){
        String[] testString = new String[10];
        testString = Arrays.copyOf(testString, testString.length *2);
        System.out.println(testString.length);
    }
}
