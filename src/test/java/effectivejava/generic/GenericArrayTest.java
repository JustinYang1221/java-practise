package effectivejava.generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 參考: https://www.jyt0532.com/2018/12/08/prefer-lists-to-arrays/
 * @author: justin
 * @date: 2022/11/15
 */
public class GenericArrayTest {

    public static void main(String[] args){
//        List<String>[] stringLists = new List<String>[1];
//
//        List<Integer> intList = List.of(42);               // (2)
//        Object[] objects = stringLists;                    // (3)
//        objects[0] = intList;                              // (4)
//        String s = stringLists[0].get(0);
//        String s = (String)((List)objects[0]).get(0);

    }

    @Test
    public void arrayTest(){
        // Fails at runtime!
        //Array是具體化的 所以Array會在運行時 才去檢查元素類型的約束
        Object[] objectArray = new Long[1];
        //objectArray[0] = "I don't fit in"; // Throws ArrayStoreExcepti

        Object[] aaa =  new List[2];
        aaa[0] = new ArrayList<Integer>(); // T為Integer

        ArrayList al = (ArrayList) aaa[0];
        al.add(111);
        String aa = (String)al.get(0); //丟出 ClassCastException

    }

    @Test
    public void recursiveTypeBoundedTest(){

    }

    @Test
    public void typeParameterTest(){

    }





}
