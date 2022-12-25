package se8.chap10inputoutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author: justin
 * @date: 2022/10/16
 */
public class IO {
    public static void dump(InputStream src, OutputStream dest) throws IOException {
        try(InputStream input = src; OutputStream output = dest){
            byte[] data = new byte[1024];
            int len = 0;
            while((len =input.read(data))!=-1){
                output.write(data, 0, len);
            }
        }
    }
}
