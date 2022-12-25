package hank.java1DPart2;
import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
public class Solution2 {
    static int currentPosition = 0;
    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        int arySize = game.length;

        List<Integer> list = Arrays.stream(game).boxed().collect(Collectors.toList());

        boolean jumpResult = false;
        boolean goNextResult = false;
        boolean isStartGoBack = false;
        while(currentPosition < arySize){
            if (!isStartGoBack){
                goNextResult = goNext(list);
                if (goNextResult){
                    continue;
                }

                jumpResult = doJump(list, leap);

                if (jumpResult == true && isSuccessEnd(arySize))
                    return true;
                else if (jumpResult == true && !isSuccessEnd(arySize)){
                    continue;
                }else {
                    //return false;
                    //jump failed, try back and jump
                    if (goBack(list, leap)) {
                        if (doJump(list, leap))
                            continue;
                        else {
                            isStartGoBack = true;
                            continue;
                        }
                    } else
                        return false;
                }
            }else{
                if (goBack(list, leap)) {
                    if (doJump(list, leap)){
                        isStartGoBack = false;
                        continue;
                    }
                    else {
                        isStartGoBack = true;
                        continue;
                    }
                } else
                    return false;
            }
        }


        if ((currentPosition+leap) >=arySize)
            return true;

        return false;
    }

    private static boolean isSuccessEnd(int arySize){
        boolean result = currentPosition >= arySize? true: false;
        return result;
    }

    private static boolean goNext(List<Integer> list){
        boolean result = false;
        if (currentPosition+1 >= list.size())
            return result;

        if (list.get(currentPosition+1) == 0) {
            currentPosition++;
            result = true;
        }
        return result;
    }

    private static boolean doJump(List<Integer> list, int leap){
        boolean result = false;
        int tempPosition = currentPosition+leap;
        if (tempPosition < list.size() && list.get(tempPosition) == 0){
            currentPosition = tempPosition;
            result = true;
        } else if (tempPosition >= list.size()){
            currentPosition = tempPosition;
            result = true;
        }

        return result;
    }

    private static boolean goBack(List<Integer> list, int leap){
        boolean result = false;
        if ((currentPosition-1) <0)
            return result;
        if (list.get(currentPosition-1) == 0){
            currentPosition --;
            result = true;
        }
        return result;
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
            currentPosition = 0;
            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}
