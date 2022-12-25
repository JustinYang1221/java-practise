package jvm.classloader.complex;

/**
 * @author: justin
 * @date: 2022/11/8
 */
public class ClassInitialization {
    {
        System.out.println("1");
    }
    static {
        System.out.println("2");
    }
    public static void main(String[] args) throws Exception {
        System.out.println("3");
        System.out.println("4: " + Subclass.var1);
        System.out.println("5");
        System.out.println("6: " + Subclass.var2); //執行完, 注意這個時候SuperClass和SubInterface和SuperInterface都已經加載好了 但都還沒初始化
        System.out.println("7");
        new Subclass();
        hello();
        SuperInterface.staticMethod();
    }
    static void hello(){
        new Hello();
    }
}
