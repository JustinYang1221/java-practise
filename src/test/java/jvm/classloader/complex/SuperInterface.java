package jvm.classloader.complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: justin
 * @date: 2022/11/8
 *
 * 主動使用類別, 引發 初始化類別的流程, 即加載、驗證、準備、解析、初始化
 *
 * 1.用了new 關鍵字 代表新物件被創建
 *
 * 2.static method被呼叫
 *
 * 3.static variable被存取(但compile-time 常數例外)
 *
 * 4.如果此類別的子類被初始化時 此類別還沒被初始化 則會先初始化此類別
 *
 * 5.從命令列被執行時 用戶指定的主類(就是public static void main(String[] args) 所在的類)
 */
public interface SuperInterface {
    //final int STATIC_1 = printRandom(); //靜態變數, 非final, 會等到第五階段:初始化時, 才對靜態變數賦值
    final int STATIC_1 = printRandom2();  //經測試, 就算final, 也不會在載入時就給予static_1初始值
    int STATIC_2 = printRandom();

    static void staticMethod() {
        System.out.println("16");
    }
    static int printRandom() {
        System.out.println((int)(Math.random() * 10));
        return 1;
    }

    static int printRandom2() {
        System.out.println(999);
        return 1;
    }
}

interface SubInterface extends SuperInterface {
    static void staticMethod() {
        System.out.println("17");
    }
}
class Superclass {
    {
        System.out.println("12");
    }
    static {
        System.out.println("13");
    }
    Superclass () {
        System.out.println("14");
    }
    {
        System.out.println("15");
    }
}

class Subclass extends Superclass implements SubInterface {
    static final int var1 = 123;
    static final int var2 = (int) (Math.random() * 10);
    {
        System.out.println("8");
    }
    static {
        System.out.println("9");
    }
    Subclass () {
        System.out.println("10");
    }
    {
        List list = new ArrayList<String>();
        list.add(list.get(list.indexOf(0)));
        list.remove(list.indexOf(0));
        System.out.println("11");
    }
}
class Hello {
    {
        System.out.println("18");
    }
    static {
        System.out.println("19");
    }
    Hello (){
        Superclass superclass = new Subclass();
    }
    {
        System.out.println("20");
    }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(0, 0, 1, 3));
        int index = list.indexOf(0);
        //int v = list.get(index);
        list.add(list.get(index));
        list.remove(list.indexOf(0));
        System.out.println(list);
    }
}


