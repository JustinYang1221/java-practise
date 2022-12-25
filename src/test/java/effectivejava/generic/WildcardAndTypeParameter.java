package effectivejava.generic;


import java.util.*;

/**
 * 類型參數與通配符的選擇
 * 兩者區別與使用時機
 * 本篇內容參考自文章:
 * https://www.jyt0532.com/2018/12/23/how-to-choose-between-wildcard-and-generic-method/
 *
 *
 * @author: justin
 * @date: 2022/11/18
 */
public class WildcardAndTypeParameter {

    //1.通配符只能讀取,不能設定
    // ? 就是指 <? extends Object>
    public static void swap(List<?> list, int i, int j) {
        //list.add(2); 編譯錯誤
        Object o = list.get(i); //可以
        //因為這邊的形式參數宣告為通配符, 因此list不能設定
        //list.set(i, list.set(j, list.get(i))); //編譯錯誤
        list.add(null); // 能加進List<?>只有null
        swapHelper(list, i, j);
    }
    //設定為類型參數
    // Private helper method for wildcard capture
    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    //2.想在一個聲明表明兩個類別的關係, 就得使用類型參數
    public static <T, S extends T> void copy(List<T> dest, List<S> src){
        dest.addAll(src);
    }

    //或是聲明兩個參數的類型都是同一個類型
    public static <T extends Number> void copy2(List<T> dest, List<T> src)
    {
            dest.addAll(src);
    }

    //若想明確的表示 dest與 src是同一個類型, 那就只能copy2的類型參數限定方式, 不能使用此種
    //因為dest有可能是List<Integer>, 而src可能是List<Long>
    public static void copy3(List<? extends Number> dest, List<? extends Number> src){

    }

    //4.1 想支持限制類型下限, 用類型參數就只能限制上限 extends
    //<T extends Number> 限制類型參數
    public static  <T extends Number> void genericExtend(List<T> list, T value){
        list.add(value);
    }
    //不能限制下線
//    public static  <T super Number> void genericExtend2(List<T> list){ //使用 super, compile不會過
//
//    }

    //4.2 如果是用通配符?, 則 上下限都ok
    //通配符限制下限, list的下限為Number以上, 因此
    public void wildCardSuper(List<? super Number> list){
        Long l = 1l;
        Integer i = 1;
        Number n = 2;
        list.add(l);
        list.add(i);
        list.add(n);
        list.add(2);
        //list.add(new Object());
        Object n2 = list.get(0);
    }

    //4.2list 的上限為Number, 也就是最高就是number但可能為long, integer
    //通配符限制上限,list的上限為Number, 因此這邊不能做add,無法得知list為integer, long, or double等.
    public void wildCardExtend(List<? extends Number> list){
        Long l = 1l;
        Integer i = 1;
        Number n = 2;
//        list.add(l);
//        list.add(i);
//        list.add(n);
//        list.add(2);
        Number n2 = list.get(0);
    }

    public static <T extends Number> void  test(List<T> data, T value){
        data.add(value);
    }

    public static void  test2(List<? super Number> data){
        data.add(1);
        Object n = data.get(0);
    }


    public static void main(String[] args){
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        //Arrays.asList提供的ArrayList是Arrays類別內的ArrayList與java.util.ArrayList不同
        //Arrays提供的ArrayList不提供add, addAll等操作
        //List<Number> numbers = Arrays.asList(4, 5, 6.0);
        List<Number> numbers2 = new ArrayList<>();
        numbers2.add(14);
        numbers2.add(15);
        numbers2.add(16);
        //copy2(numbers2, integerList);
        copy(numbers2, integerList);
        System.out.println(numbers2);
        copy3(integerList, numbers2);
        genericExtend(numbers2, 88);
        System.out.println(numbers2);
        Stack<Number> stack = new Stack<>();
        stack.pop();
        stack.push(1);

    }

    class NewStack<E> extends Stack<E> {
        //此為Producer-Extends, 是指對於整個Stack來說, pushAll是生產, 但對於src來說是讀取元素出來
        public void pushAll(Iterable<? extends E> src){
            for(E v : src){
                push(v);
            }

            //src.add(pop()); // 編譯不過, 解釋: 假如E是Number, 但傳進來的src是 Iterable<Integer>,
                            // Integer是 Number的子類別, 因此可以傳入, 但 Iterable<Integer>是無法加入 Number元素.
        }

        //此為Consumer-super, 對Stack來說 popAll方法是讀出元素, 就是消費
        public void popAll(Collection<? super E> dst) {
            while (!isEmpty())
                dst.add(pop());
        }
    }


}

class NewStack2<E>  {
    //此為Producer-Extends, 是指對於整個Stack來說, pushAll是生產, 但對於src來說是讀取元素出來
    public void pushAll(Iterable<? extends E> src){
        for(E v : src){

        }

        //src.add(pop()); // 編譯不過, 解釋: 假如E是Number, 但傳進來的src是 Iterable<Integer>,
        // Integer是 Number的子類別, 因此可以傳入, 但 Iterable<Integer>是無法加入 Number元素.
    }

    //此為Consumer-super, 對Stack來說 popAll方法是讀出元素, 就是消費
//    public static void popAll(Collection<? super E> dst) {
//
//        dst.add(null);
//    }
}
