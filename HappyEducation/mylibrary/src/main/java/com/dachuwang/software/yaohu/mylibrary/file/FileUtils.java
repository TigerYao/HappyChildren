package com.dachuwang.software.yaohu.mylibrary.file;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

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

    public static File[] findFileByNameContain(String dir,final String name){
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                s = s.toLowerCase();
                String filename = name.toLowerCase();
                if(s.contains(filename)){
                    return true;
                }
                return false;
            }
        };
        File file = new File(dir);
        if(file!=null&&file.exists()&&file.isDirectory()){
            return file.listFiles(filenameFilter);
        }
        return null;
    }

    public static String[] findFileByNameEqual(String dir,final String name){
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                if(s.equalsIgnoreCase(name)){
                    return true;
                }
                return false;
            }
        };
        File file = new File(dir);
        if(file!=null&&file.exists()&&file.isDirectory()){
            return file.list(filenameFilter);
        }
        return null;
    }


}
