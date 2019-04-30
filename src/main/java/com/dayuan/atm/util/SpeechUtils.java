package com.dayuan.atm.util;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.dayuan.atm.exception.BizException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;


public class SpeechUtils {
    public static final String APP_ID = "16039485";
    public static final String API_KEY = "9scSDTHqOAsaSDZp7OMqkb2T";
    public static final String SECRET_KEY = "l6prP08NbsxByeKkKWIZIrFCOIKfhBZp";

    private SpeechUtils() {
    }

    private static AipSpeech client;

    //单例模式-懒汉模式，创建对象
    public static AipSpeech getInstance() {
        if (client == null) {
            //加锁，保证线程安全
            synchronized (AipSpeech.class) {
                if (client == null) {
                    client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
                }
            }
        }
        return client;
    }

    public static byte[] SpeechSynthesizer(String word) {
        int maxLength = 1024;

        if (word.getBytes().length >= maxLength) {
            //return false;
            throw new BizException("speechError");
        }

        client = getInstance();

        TtsResponse res = client.synthesis(word, "zh", 1, null);
        byte[] data = res.getData();

        //JSONObject res1 = res.getResult();
        //if (data != null) {
        //    try {
        //        Util.writeBytesToFileSystem(data, outputPath);
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //    return true;
        //}

        //if (res1 != null) {
        //    log.info(" result : " + res1.toString());
        //}
        //return false;

        return data;
    }


    //public static void main(String[] args) {
    //    SpeechSynthesizer("11", "/Users/huangkaiwen/Downloads/123.mp3");
    //}

}
