package hank.dequeue;

import java.util.*;

/**
 * @author: justin
 * @date: 2022/11/16
 */
public class DequeTest2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new LinkedList<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int max = 0;
        HashSet<Integer> set = new HashSet<>();
        int setSize = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.add(num);
            set.add(num);
            if (deque.size() == m ){

                setSize = set.size();
                if (setSize > max)
                    max = setSize;

                int val = deque.removeFirst();
                if (!deque.contains(val))
                    set.remove(val);
            }

        }
        System.out.println(max);
    }
}
