package se8.chap9collection;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: justin
 * @date: 2022/10/16
 */
public class Chapter_9_practice {

    /***
     * 實作第二題
     * 從前面看字元跟後面看字元順序, 順序是相同的
     */
    @Test
    public void stringCorrectTest(){
        String[] words = {"RADAR", "WATER START", "MILK KLIM", "RESERVERED", "IWI"};
        ArrayDeque<Character> charQueue = new ArrayDeque<>();

        ArrayList<String> answerList = new ArrayList<>();
        String reverseWord = "";
        for(String word: words){
            //一般字串解法
            int len = word.length() -1;
            reverseWord = "";
            for(int i=len; i >-1; i--){
                reverseWord+= word.charAt(i);
            }
            System.out.println("reverseWord="+reverseWord);
            if (word.equals(reverseWord))
                answerList.add(word);
        }
        System.out.println("answer:" +answerList);
        //dequeue 解法
//        for(int i=0; i< text.length(); i++){
//            charQueue.add(text.charAt(i));
//        }
//        String a = "";
//        while(!charQueue.isEmpty()){
//            a+= charQueue.pollLast();
//        }
//        System.out.println("a="+a);


    }

    /***
     * 第一題
     */
    class IterableString {
        String texts;
        public IterableString(String text){
            this.texts = text;
        }

        public void getAllString(){
            char[] chars = texts.toCharArray();
            for(char c: chars){
                System.out.println(c + ",");
            }
        }
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        se8.chap9collection.IterableString iterableString = new se8.chap9collection.IterableString("abcckdjfdk;afhjkdl;");
        iterableString.getAllString();


    }
}
