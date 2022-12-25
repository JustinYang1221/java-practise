package hank.dequeue;

import java.util.*;

/**
 * @author: justin
 * @date: 2022/11/16
 */
public class DequeTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new LinkedList<>();
        int n = in.nextInt();
        int m = in.nextInt();

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.add(num);
        }

        int size = 0;
        //int nSub1 = n - 1;
        Set<Integer> subSet = new TreeSet<>();

        int maxUnique = 0;
        int subSetUnique = 0;
        List<Integer> tempList = new ArrayList<>();
        while((size=deque.size()) >= m){
            subSet.add(deque.remove());
            tempList = (List<Integer>)deque;
            for(int i=0; i< m-1; i++){
                subSet.add(tempList.get(i));
            }
            subSetUnique = subSet.size();
            if (subSetUnique == m){
                maxUnique = subSetUnique;
                break;
            }

            if (subSetUnique > maxUnique)
                maxUnique = subSetUnique;

            subSet.clear();
        }
        System.out.println(maxUnique);
    }
}
