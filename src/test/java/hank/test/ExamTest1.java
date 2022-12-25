package hank.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @author: justin
 * @date: 2022/11/16
 */
public class ExamTest1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int textCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> text = IntStream.range(0, textCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<String> result = ExamTest1.funWithAnagrams(text);

       System.out.println(result);

        bufferedReader.close();

    }

    public static List<String> funWithAnagrams(List<String> text) {
        int len = text.size();
        String currentStr = "";
        int currentStrLength = 0;
        char[] currentChar;
        char[] otherChar;
        String orderStr = "";
        String otherOrderStr = "";
        List<String> result = new ArrayList<>();
        for(int i=0; i< len; i++){
            currentStr =  text.get(i);
            currentStrLength = currentStr.length();
            if (currentStrLength == 0)
                continue;

            for(int j=i+1; j< len; j++){
                if (currentStrLength == text.get(j).length()){
                    currentChar = currentStr.toCharArray();
                    otherChar = text.get(j).toCharArray();
                    Arrays.sort(currentChar);
                    Arrays.sort(otherChar);
                    orderStr = new String(currentChar);
                    otherOrderStr = new String(otherChar);
                    if (orderStr.equals(otherOrderStr))
                        text.set(j, "");

                }
            }
        }
        text = text.stream().filter(s->!s.equals("")).sorted().collect(toList());
//        Object[] textArry = text.toArray();
//        Arrays.sort(textArry);
//        result = Stream.of(textArry).map(o-> (String)o).collect(Collectors.toList());
        return text;
    }
}
