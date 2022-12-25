package se8.chap12Lamda;

import java.util.Arrays;

/**
 * 每一個方法都是 Comparator的compare方法的簽署
 * @author: justin
 * @date: 2022/10/19
 */
public class StringOrder {

    public static int byLength(String s1, String s2){
        return s1.length() - s2.length();
    }

    //按照字典順序排序(自然排序)
    public static int byLexicography(String s1, String s2){
        return s1.compareTo(s2);
    }

    public static int byLexicographyIgnoreCase(String s1, String s2){
        return s1.compareToIgnoreCase(s2);
    }

    public static void main(String[] args){
        String[] names = {"john", "catepillar", "busha"};
        Arrays.sort(names, StringOrder::byLength);
        System.out.println(Arrays.toString(names));

        Arrays.sort(names, String::compareTo);
        System.out.println(Arrays.toString(names));

    }
}
