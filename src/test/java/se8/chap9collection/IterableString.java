package se8.chap9collection;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: justin
 * @date: 2022/10/10
 */
public class IterableString {
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



    public static void main(String[] args) throws NoSuchAlgorithmException {
        IterableString iterableString = new IterableString("abcckdjfdk;afhjkdl;");
        iterableString.getAllString();


    }
}
