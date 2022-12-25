package hank.java1DPart2;

/**
 * Best Solution
 * 參考別人的寫法
 * Java 1D Array (Part 2)
 * @author: justin
 * @date: 2022/11/14
 */
import java.util.*;

public class TrySolution {

    public static boolean[] trackAllInex(int leap, int[]game) {
        int n = game.length;
        boolean[] pointValidPool = new boolean[n]; //if point is been tracked, the value is true.
        // First one is always valid because that's where we start (always a 0;)
        pointValidPool[0] = true;
        Queue<Integer> trackedIndexQueue = new LinkedList<>(); // try to Keeps all the indexes we can track.
        trackedIndexQueue.add(0);
        while(!trackedIndexQueue.isEmpty()) {
            int curr = trackedIndexQueue.poll();
            // Check if you can put a true in forward, backward and leapForward in pointValidPool
            //Forward 1, the pointValidPool will be false when first time track the index
            //when the same index are tracked, the index will not be added to queue again.
            if(curr+1<n && game[curr+1]==0 && !pointValidPool[curr+1]) {
                pointValidPool[curr+1] = true;
                trackedIndexQueue.add(curr+1);
            }

            //leapForward
            if(curr+leap<n && game[curr+leap]==0 && !pointValidPool[curr+leap]){
                pointValidPool[curr+leap] = true;
                trackedIndexQueue.add(curr+leap);
            }

            //Back 1;
            if(curr-1>=0 && game[curr-1]==0 && !pointValidPool[curr-1]) {
                pointValidPool[curr-1] = true;
                trackedIndexQueue.add(curr-1);
            }


        }
        return pointValidPool;
    }
    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        boolean canWin = false;
        int n = game.length;
        boolean[] validIndexPool = trackAllInex(leap, game);
        for(int i=0; i<n; i++){
            if(validIndexPool[i]){
                if(i == n-1 || i+leap >= n){
                    canWin = true;
                    break;
                }
            }else
                continue;
        }

        return canWin;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}
