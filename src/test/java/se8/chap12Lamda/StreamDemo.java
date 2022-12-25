package se8.chap12Lamda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author: justin
 * @date: 2022/10/20
 */
public class StreamDemo {

    public static void main(String[] args) {
        Integer[] values = {1, 3, 5, 7, 9};
        List<Integer> list = Stream.of(values)
                .filter(value -> value > 3)
                .collect(Collectors.toList());

        out.println(list);
    }

    @Test
    public void pipeLineTest() {
        List<Player> players = Arrays.asList(new Player("Justin", 39),
                                             new Player("Monica", 36),
                                             new Player("Irene", 6)
                );

        players.stream().filter(p->p.getAge() > 15)
//                .map(p-> p.getName())
//                .map(n-> n.toUpperCase())
                .map(Player::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList())
                .forEach(out::println);
    }

    @Test
    public void arrayToStreamTest(){
        String[] ary = {"a", "b", "c"};
        List<String> listStr = Stream.of(ary).collect(Collectors.toList());

        List<String> listStr2 = Arrays.asList(ary);

        System.out.println(listStr);
        System.out.println(listStr2);
    }

    class Player {
        private String name;
        private Integer age;

        public Player(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}
