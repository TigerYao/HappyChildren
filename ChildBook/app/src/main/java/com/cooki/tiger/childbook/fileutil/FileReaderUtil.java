package com.cooki.tiger.childbook.fileutil;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by yaohu on 15/10/6.
 */
public class FileReaderUtil {

    public static String[] getFiles(String path){

        File file = new File(path);
        if(file.exists()){
            if(file.isDirectory()){
                File files[]=file.listFiles();
                String fileNames[] = new String[files.length];
                for(int i=0;i<files.length;i++){
                    fileNames[i] = files[i].getAbsolutePath();
                    Log.i("tag",fileNames[i]);
                }
                return fileNames;
            }else
                return new String[]{path};
        }
        return null;
    }


    public static File[] getFile(String path){
        File file = new File(path);
        if(file.exists()){
            if(file.isDirectory()){
                File files[]=file.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        if(filename.endsWith(".jpg")){
                            return true;
                        }
                        return false;
                    }
                });
                Arrays.sort(files);
                return files;
            }
        }
        return null;
    }
//    public static Drawable[] getFilesName(File file){
//        Drawable[] drawables = new Drawable[files.length+1];
//        for(int i=1;i<=23;i++){
//             drawables[i]= Drawable.createFromPath(files[i-1].getAbsolutePath());
//
//        }
//        return drawables;
//    }

    public static File getFileByName(String path){
        File file = new File(path);
        if(file.exists()&&file.isFile()){
            return file;
        }
        return null;
    }

}
