package jvm.classloader;

/**
 * @author: justin
 * @date: 2022/11/8
 */
public class TestBootstrap {

    public static void main(String[] args) {

        ClassLoader cl = new CustomClassLoader();
        while (cl!=null){
            System.out.println(cl);
            cl = cl.getParent();
        }
    }
    private static class CustomClassLoader extends ClassLoader {}

    public String test(){
        return null;
    }




}


