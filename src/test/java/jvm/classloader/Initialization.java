package jvm.classloader;

/**
 * @author: justin
 * @date: 2022/11/8
 */


public class Initialization {
    //靜態變數宣告在靜態區塊之後, 那就只能賦值, 不能在靜態區塊存取
    static {
        i = 122;
        //System.out.println(i);//illegal forward reference
    }
    static int i;
}
