package se8.chap10inputoutput;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author: justin
 * @date: 2022/10/16
 */
public class Copy {
    public static void main(String[] args) throws IOException {
        IO.dump(new FileInputStream(args[0]), new FileOutputStream(args[1]));

        //透過命令列執行 開啟來源檔案複製到目的檔案
        //java se8.inputoutput.Copy C:\xxxx\test.java C:\dest\test.txt
    }


}
