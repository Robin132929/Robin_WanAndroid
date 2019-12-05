package com.robin.robin_wanandroid.aaa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Process;
import android.os.SystemClock;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import android.util.LogPrinter;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class merge88 {
    //给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
    //
    //说明:
    //
    //初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
    //你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
    //示例:
    //
    //输入:
    //nums1 = [1,2,3,0,0,0], m = 3
    //nums2 = [2,5,6],       n = 3
    //
    //输出: [1,2,2,3,5,6]
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/merge-sorted-array
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// -4 -1 1 2
//    class Solution {
//        public int threeSumClosest(int[] nums, int target) {
//            Arrays.sort(nums);
//            int len = nums.length ;
//            int h,t,sum=Integer.MAX_VALUE,diff=Integer.MAX_VALUE;
//            for (int i = 0; i <len-2; i++) {
//                h=i+1;
//                t=len-1;
//                while (h<t){
//                    diff=Math.abs(nums[i]+nums[h]+nums[t]-target)<diff ? Math.abs(nums[i]+nums[h]+nums[t]-target):diff;
//                    sum=Math.abs(nums[i]+nums[h]+nums[t]-target)<diff ?nums[i]+nums[h]+nums[t]:sum;
//                    System.out.println(diff+"  "+sum);
//                    h++;
//                    t--;
//                }
//            }
//            return sum;
//        }
//    }
//   static int b=0;
//    public static void getInstance(){
//
//    }
//    private static class a {
//        b=1;
//    }
//
//
//
//    private static Context context;
//    private static  String strin;
//    private JRPro(){
//    }
//    private static class Singleton{
//        private static  JRPro jrPro=new JRPro();
//    }
//
//    private static void start(Context context,String slotid){
//        if (Singleton.jrPro==null);
//
//    }
//    class Solution {
//        public void nextPermutation(int[] nums) {
//            int len=nums.length;
//            for (int i = len-1; i >0 ; i--) {
//                if (nums[i]>nums[i-1]){
//                    int temp=nums[i];
//                    nums[i]=nums[i-1];
//                    nums[i-1]=temp;
//                    return;
//                }
//            }
//            int h=0,t=len-1;
//            while (h<t){
//                int temp=nums[h];
//                nums[h]=nums[t];
//                nums[t]=temp;
//                h++;
//                t--;
//            }
//
//
//        }
//    }

    public static void main(String[] args) {
        Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "ActivityThreadMain");

        // CloseGuard defaults to true and can be quite spammy.  We
        // disable it here, but selectively enable it later (via
        // StrictMode) on debug builds, but using DropBox, not logs.
        CloseGuard.setEnabled(false);

        Environment.initForCurrentUser();

        // Set the reporter for event logging in libcore
        EventLogger.setReporter(new EventLoggingReporter());

        // Make sure TrustedCertificateStore looks in the right place for CA certificates
        final File configDir = Environment.getUserConfigDirectory(UserHandle.myUserId());
        TrustedCertificateStore.setDefaultUserDirectory(configDir);

        Process.setArgV0("<pre-initialized>");
        //主线程looper
        Looper.prepareMainLooper();
        //创建主线程
        ActivityThread thread = new ActivityThread();
        //调用attach 创建application 传入false
        thread.attach(false);

        if (sMainThreadHandler == null) {
            sMainThreadHandler = thread.getHandler();
        }

        if (false) {
            Looper.myLooper().setMessageLogging(new
                    LogPrinter(Log.DEBUG, "ActivityThread"));
        }
        //为了便于理解 ，我们假定现在是从子线程发消息给主线程（UI线程）经过上面的分析此时Message已经从子线程发出并送到MessageQueue
        //此时在主线程执行Looper.loop 便可以将MessageQueue中的Message取出并分发 loop取出一个message 之后进入一个无限循环中 不断调用MessageQueue的next方法
        //next方法的主要作用是从MessageQueue中取出Messade
        //最后调用handler的dispatchMessage分发Message  在dispatchMessage方法中进行Message的处理 首先会判断该Message的callback是否为空
        // 如果为空 则判断handler的callback是否为空 如果handler的callback为空才会执行handleMessage 该方法就是我们重写的handleMessage方法
        // 至此我们回到主线程


        // End of event ActivityThreadMain.
        Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
        //开启looper
        Looper.loop();

        throw new RuntimeException("Main thread loop unexpectedly exited");
    }


            Log.i("intent", "invoke: " +method.getName());

            if (method.getName().equals("getIntentSender")){
        for (Object obj : args) {

            if (obj instanceof Intent[]){
                Intent [] intents= (Intent[]) obj;
                Uri uri = intents[0].getData();
                Log.i("intent", "invoke:  11" + uri.toString());
                intents[0].setData(Uri.parse("tenvideo2://?action=1&cover_id=zr5a67l333ehzu9&video_id=n00320bp8yr&from=30090|1913|20191016|732"));

            }
        }
    }

}
