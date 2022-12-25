package jvm.classloader;

/**
 * @author: justin
 * @date: 2022/11/8
 * 1.所有的靜態區塊 會照順序被合併在一起(包含靜態變數宣告)
 * 2.所有的Instance Initializer 會照順序被合併在一起
 * 3.Instance Initializer 會被移到constructor之前執行
 *
 * 也就是跑完所有的靜態區塊程式碼(類別初始化),再跑 instance Initializer(物件初始化),
 * 最後才跑Constructor.
 */
public class StaticOrder {
    static final int var1 = 123;
    static final int var2 = (int) (Math.random() * 10);
    {
        System.out.println("1");
    }
    static {
        System.out.println("2");
    }
    StaticOrder () {
        System.out.println("3");
    }
    {
        System.out.println("4");
    }
    static {
        System.out.println("5");
    }
    static {
        System.out.println("6");
    }
    static int a(){
        System.out.println("7");
        return 1;
    }
    static final int var3 = a();

    public static void main(String[] args) throws Exception {
        new StaticOrder();
    }

    /**
     * 答案
     * 2
     * 5
     * 6
     * 7
     * 1
     * 4
     * 3
     */
}
