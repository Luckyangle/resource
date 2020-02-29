package com.giantan.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Lvzhihang
 * 2020/2/28
 * defination：
 */
public class FileUtil {

    public static String receiveFromStream(InputStream inputStream){
        StringBuilder result = new StringBuilder(); String temp;
        try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
            while((temp = bufferedReader.readLine()) != null){
                result.append(temp.getBytes());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String receiveFromMultipartFile(MultipartFile file){
        StringBuilder result = new StringBuilder(); String temp;
        try(InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
            while((temp = bufferedReader.readLine()) != null){
                result.append(temp + "\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
