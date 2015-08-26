package com.dachuwang.software.yaohu.mylibrary.file;
import java.io.File;
/**
 * Created by yaohu on 15/8/26.
 * email yaohu@dachuwang.com
 */
public class FileUtils {

    public static File[] getFiles(String dir){
        File file = new File(dir);
        if(file.exists()&&file.isDirectory()){
            return file.listFiles();
        }
        return null;
    }

}
