package se8.chap12Lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.System.out;

/**
 * @author: justin
 * @date: 2022/10/20
 */


class Person {
    String name;

    Person(String name){
        this.name = name;
    }

    public String toString(){
        return "Person{name= "+ name + "}";
    }
}

public class MethodReferenceDemo {
    /***
     * map方法 將集合P轉換為集合R, 透過實作函式介面 Function<P, R>
     *
     * @param list
     * @param mapper
     * @param <P>
     * @param <R>
     * @return
     */
    static <P, R> List<R> map(List<P> list, Function<P,R> mapper){
        List<R> mapped = new ArrayList<>();
        for(P p : list){
            mapped.add(mapper.apply(p));
        }
        return mapped;
    }

    public static void main(String[] args){
        List<String> names = Arrays.asList("justin", "caster", "song", "rex");
        //Lamda 物件的建構式參考
        //Function<T, R> 定義了方法 R apply(T t), 實例必須指定如何將 T轉換為R
        // List<Person> persons = map(names, name-> new Person(name)); 原寫法
        List<Person> persons = map(names, Person::new); //簡化寫法
        //方法參考, println的方法簽署與 Consumer介面的 void accept(T t)方法一樣
        //故可以直接使用方法參考
        persons.forEach(out::println);
    }
}
