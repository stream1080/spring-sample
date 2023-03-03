package com.example.demo.utils;

import java.util.UUID;

/**
 * @author stream
 */
public class UUIDUtil {

    /**
     * 16 位 uuid
     *
     * @return
     */
    public static String traceId() {
        return UUID.randomUUID().toString().replace("-", "").substring(16);
    }
}
