package com.robin.robin_wanandroid.aaa;

import android.content.Context;

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

}
