package jvm.gc;

import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 參考本篇 每個程序員都該瞭解的JVM - 垃圾收集器
 * https://www.jyt0532.com/2020/03/12/garbage-collector/
 * @author: justin
 * @date: 2022/10/26
 */
public class MemoryRefGCCollectDemo {


    @Test
    public void memoryReferenceTypeTest(){
        //1.強引用, 只要物件(對象)可到達, 就不會被回收
        Object obj = new Object();

        //2.軟引用,只有在內存(heap)即將要發生 內存溢出(outOfMemoryError), 才會被回收
        //比較常見的是Cache
        SoftReference<Object> softReference = new SoftReference<>(obj);
        obj = null;

        Object obj2 = new Object();
        //3.弱引用(Weak Reference), 對象若為弱引用可達, 則無論內存是否足夠, 都會在下一次的
        //gc被回收
        WeakReference<Object> weakReference = new WeakReference<>(obj2);
        obj2 = null;
        weakReference.clear(); //清除掉弱引用, obj2完全沒人指

        //4.虛引用(Phantom reference), 虛引用基本上對於垃圾回收器來說等於沒有引用, 虛引用的
        //目的只是當這個物件被回收時, 我們的ReferenceQueue(若有在建構虛引用時傳入)會得到一個通知
        Object obj3 = new Object();
        PhantomReference<Object> phantomReference = new PhantomReference<Object>(obj3, null);
        obj3 = null;

    }
}
