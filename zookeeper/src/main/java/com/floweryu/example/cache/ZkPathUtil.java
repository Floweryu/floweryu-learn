package com.floweryu.example.cache;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Floweryu
 * @date 2022/3/20 14:46
 */
public class ZkPathUtil {
    private static final String FOLDER_SEPARATOR = "/";

    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

    public static String getPath(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(FOLDER_SEPARATOR).append(string.trim());
        }
        return sb.toString();
    }

    public static void check(String path) {
        if (StringUtils.isBlank(path)) {
            throw new  IllegalArgumentException("path should not be blank");
        }
        if (path.contains(FOLDER_SEPARATOR) || path.contains(WINDOWS_FOLDER_SEPARATOR)) {
            throw new IllegalArgumentException("path should not contains '/' or '\\'");
        }
    }

    public static String[] splitPath(String path) {
        return path.split(FOLDER_SEPARATOR);
    }
}
