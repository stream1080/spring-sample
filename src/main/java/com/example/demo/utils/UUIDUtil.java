package com.example.demo.utils;

import java.util.UUID;

/**
 * @author stream
 */
public class UUIDUtil {

    /**
     * 去掉UUID的"-"
     *
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
