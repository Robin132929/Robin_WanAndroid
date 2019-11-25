package com.robin.robin_wanandroid.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

public class CacheUtils {

    /**
     * 获取当前所有缓存 占用空间大小
     * @param context
     * @return
     */
    public static String getTotalCacheSize(Context context){
        long cacheSize=getSize(context.getCacheDir());
        if (Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED){
            cacheSize+=getSize(context.getExternalCacheDir());
        }
        return formatSize(cacheSize);
    }

    /**
     * 清除当前所有缓存
     * @param context
     */
    public static void clearAllCache(Context context){
        delete(context.getCacheDir());
        if (Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED){
            delete(context.getExternalCacheDir());
        }
    }
    /**
     * 删除传入的file
     * @param file
     * @return
     */

    private static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String c : children) {
                boolean success = delete(new File(file, c));
                if (!success) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /**
     * 获取当前文件夹大小
     * 获取文件
     * Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/
     * 目录，一般放一些长时间保存的数据
     * Context.getExternalCacheDir() -->
     * SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     * @param file
     * @return
     */
    private static long getSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                if (f.isDirectory()) {
                    size = size + getSize(f);
                } else {
                    size = size + f.length();
                }
            }
        } catch (Exception ignore) {
        }
        return size;
    }


    /**
     * 格式化单位
     */
    private static String formatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0KB";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
