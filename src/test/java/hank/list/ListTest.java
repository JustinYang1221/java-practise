package hank.list;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author: justin
 * @date: 2022/11/15
 */
public class ListTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        //System.out.println(strAry.toString());
        List<Integer> list = Arrays.stream(br.readLine().split(" ")).
                map(s -> Integer.parseInt(s)).collect(Collectors.toList());


        int q = Integer.parseInt(br.readLine());
        String operation = "";
        int[] indexValues = new int[2];
        int i = 0;
        while(q-- > 0){
            operation = br.readLine();
            switch (operation){
                case "Insert":{
                    i =0;
                    for(String s: br.readLine().split(" ")){
                        indexValues[i++] = Integer.parseInt(s);
                    }
                    list.add(indexValues[0], indexValues[1]);
                    break;
                }

                case "Delete":{
                    i = Integer.parseInt(br.readLine());
                    list.remove(i);
                    break;
                }
            }


        }
        for(Integer v : list){
            System.out.print(v+ " ");

        }
    }
}
