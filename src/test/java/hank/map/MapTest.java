package hank.map;

import java.io.IOException;
import java.util.*;

/**
 * @author: justin
 * @date: 2022/11/15
 */
public class MapTest {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        in.nextLine();

        Map<String, Integer> phoneBook = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            phoneBook.put(in.nextLine(), in.nextInt());
            in.nextLine();
        }

        List<String> result =  new ArrayList<>();
        Integer phoneNumber;
        while(in.hasNext())
        {
            String queryName=in.nextLine();
            phoneNumber = phoneBook.get(queryName);
            if (Objects.nonNull(phoneNumber))
                System.out.println(queryName+"="+phoneNumber);
            else
                System.out.println("Not found");
        }

        for(String v: result){
            System.out.println(v);
        }

    }
}
