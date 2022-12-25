package hank.java1DPart2;
import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/***
 *  * Java 1D Array (Part 2)
 * 我自己寫的, 效能過不了
 */

public class Solution {
    static int currentPosition = 0;
    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        int arySize = game.length;

        //List<Integer> list = Arrays.stream(game).boxed().collect(Collectors.toList());

        boolean jumpResult = false;
        boolean goNextResult = false;
        boolean goBackJumpResult = false;
        boolean isStartGoBack = false;
        while(currentPosition < arySize){
            if (!isStartGoBack){
                goNextResult = goNext(game);
                if (goNextResult){
                    continue;
                }

                jumpResult = doJump(game, leap);

                if (jumpResult == true && isSuccessEnd(arySize))
                    return true;
                else if (jumpResult == true && !isSuccessEnd(arySize)){
                    continue;
                }else {
                    //return false;
                    //jump failed, try back and jump
                    if (goBack(game, leap)) {
                        if (doJump(game, leap))
                            continue;
                        else {
                            isStartGoBack = true;
                            continue;
                        }
                    } else
                        return false;
                }
            }else{
                if (goBack(game, leap)) {
                    if (doJump(game, leap)){
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
        boolean result = currentPosition == arySize-1 ? true: false;
        return result;
    }

    private static boolean goNext(int[] game){
        boolean result = false;
        if (currentPosition+1 >= game.length)
            return result;

        if (game[currentPosition+1] == 0) {
            currentPosition++;
            result = true;
        }
        return result;
    }

    private static boolean doJump(int[] game, int leap){
        boolean result = false;
        int tempPosition = currentPosition+leap;
        int length = game.length;
        if (tempPosition < length && game[tempPosition] == 0){
            currentPosition = tempPosition;
            result = true;
        } else if (tempPosition >= length){
            currentPosition = tempPosition;
            result = true;
        }

        return result;
    }

    private static boolean goBack(int[] game, int leap){
        boolean result = false;
        if ((currentPosition-1) <0)
            return result;
        if (game[currentPosition-1] == 0){
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
