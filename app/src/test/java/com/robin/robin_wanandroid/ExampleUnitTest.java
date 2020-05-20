package com.robin.robin_wanandroid;

import org.junit.Test;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static android.util.Log.println;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
//        println("12.345-6.A".split("\\.|-".toRegex()));
        int [] a=new int[]{2,3,1,2,4,9,6,5,7};
        int [] b=sort(a);
        for (int i = 0; i < b.length; i++) {
            System.out.println(i+"     " +b[i]);
        }
    }

    public boolean exist(char[][] board, String word) {
  char [] words=word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,words,i,j,0)){
                    return true;
                }
            }
        }
           return false;
    }
    public boolean dfs(char[][] board,char[] word,int i,int j,int k){
                if (board[i][j]!=word[k]||i<0||i>board.length||j<0||j>board[0].length) return false;
                if (k==word.length-1) return true;
                char temp=board[i][j];
        board[i][j]=' ';
                if (dfs(board,word,i-1,j,k+1)||dfs(board,word,i+1,j,k+1)||dfs(board,word,i,j-1,k+1)||dfs(board,word,i,j+1,k+1)){
                    return true;
                }
                board[i][j]=temp;

      return false;
    }
     public class TreeNode {
      int val;
      TreeNode left;
     TreeNode right;
      TreeNode(int x) { val = x; }
  }
    int post_index;
    int [] inorder;
    int [] postorder;
    HashMap<Integer,Integer> map=new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder=inorder;
        this.postorder=postorder;
        post_index=postorder.length-1;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return buildsonTree(0,inorder.length-1);
    }

    public TreeNode buildsonTree(int left,int right){
      if (left>right){
          return null;
      }
        TreeNode root=new TreeNode(this.postorder[post_index]);
        post_index--;
        root.right=buildsonTree(map.get(root.val)+1,right);
        root.left=buildsonTree(left,map.get(root.val)-1);
        return root;
    }


    class Solution {
        int post_idx;
        int[] postorder;
        int[] inorder;
        HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

        public TreeNode helper(int in_left, int in_right) {
            // if there is no elements to construct subtrees
            if (in_left > in_right)
                return null;

            // pick up post_idx element as a root
            int root_val = postorder[post_idx];
            TreeNode root = new TreeNode(root_val);

            // root splits inorder list
            // into left and right subtrees
            int index = idx_map.get(root_val);

            // recursion
            post_idx--;
            // build right subtree
            root.right = helper(index + 1, in_right);
            // build left subtree
            root.left = helper(in_left, index - 1);
            return root;
        }

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.postorder = postorder;
            this.inorder = inorder;
            // start from the last postorder element
            post_idx = postorder.length - 1;

            // build a hashmap value -> its index
            int idx = 0;
            for (Integer val : inorder)
                idx_map.put(val, idx++);
            return helper(0, inorder.length - 1);
        }
    }

    //avl左旋
    public TreeNode leftrun(TreeNode root){
        if (root==null){
            return null;
        }

        TreeNode temp=root.right;//右子为父
        TreeNode left=null;
        if (root.left!=null){
          left=root.left;
        }
        temp.left=root; //父为左子
        if (left!=null){
            root.right=left; //左孙遍右孙
        }

        return temp;
    }

    public String tictactoe(int[][] moves) {
        int[] statistics=new int[8];
        int len=moves.length;
        for (int i = len-1; i >0 ; i-=2) {
            statistics[moves[i][0]]++;
            statistics[moves[i][1]+3]++;
            if (moves[i][0]==moves[i][1]){
                statistics[6]++;
            }

            if (moves[i][0]+moves[i][1]==2){
                statistics[7]++;
            }
        }

        for (int i = 0; i < statistics.length; i++) {
            if (statistics[i]==2){
                return len%2==0? "B" :"A";
            }
        }
        if (len==9){
            return "Draw";
        }
        return "Pending";
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (dfs(root,limit)){
            return null;
        }
        return root;
    }

    public boolean dfs(TreeNode root,int limit){
        if (root.left==null&&root.right==null){
            return root.val<limit;
        }
        boolean ldel=true;
        boolean rdel=true;
        if (root.left!=null){
            ldel=dfs(root.left,limit-root.val);
        }

        if (root.right!=null){
            rdel=dfs(root.right,limit-root.val);
        }
  if (ldel){
root.left=null;
        }
        if (rdel){
root.right=null;
        }

            return ldel&&rdel;
    }

    public boolean checkStraightLine(int[][] coordinates) {
        DecimalFormat df=new DecimalFormat("0.00");

       df.format( (double) (coordinates[0][1]-coordinates[1][1])/(coordinates[0][0]-coordinates[1][0]));
      double l=  (coordinates[0][1]-coordinates[1][1])/(coordinates[0][0]-coordinates[1][0]);
        for (int i = 0; i <coordinates.length ; i++) {
            double n= (coordinates[0][1]-coordinates[i][1])/(coordinates[0][0]-coordinates[i][0]);
         if (n!=l){
             return false;
         }
        }
        return true;
    }

    public List<String> generateParenthesis(int n) {
      List<String> result=new ArrayList<>();
      String s="";
      dfs(result,0,0,s,2*n);
      return result;

    }
    public void dfs(List<String> list,int left,int right,String s,int total){
        if (s.length()==total){
            list.add(s);
            return;
        }
        if (2*left+1<=total){
            dfs(list,left+1,right,s+"(",total);
        }
        if (right+1<=left){
            dfs(list,left,right+1,s+")",total);

        }

        return;
    }
    public boolean judgeCircle(String moves) {
      int left=0,up=0;
        for (char move : moves.toCharArray()) {
            if (move=='R'){
                left--;
            }
            if (move=='L'){
                left++;
            }
            if (move=='U'){
                up++;
            }
            if (move=='D'){
                up--;
            }
        }
        return left==0&&up==0;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<TreeNode> nodes=new LinkedList<>();
        List<List<Integer>> list=new ArrayList<>();
        LinkedList<Integer> ceng=new LinkedList<>();
        TreeNode currentNode=null;
        nodes.add(root);
        nodes.add(null);
        boolean left=true;
        if (nodes.size()>0){
            currentNode=nodes.pollFirst();
            if (currentNode!=null){
                if (left){
                    System.out.println("ceng1 :"+currentNode.val);

                    ceng.addLast(currentNode.val);
                }else {
                    System.out.println("ceng2 :"+currentNode.val);

                    ceng.addFirst(currentNode.val);
                }
                if (currentNode.left!=null){
                    nodes.add(currentNode.left);
                }
                if (currentNode.right!=null){
                    nodes.add(currentNode.right);
                }
            }else {
                for (Integer integer : ceng) {
                    System.out.println("ceng :"+integer);

                }
                list.add(ceng);
                ceng= new LinkedList<>();
                if (nodes.size()>0){
                    nodes.add(null);

                }
                left=!left;
            }

        }

  return list;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        for (char letter : letters) {
            if (target-letter<0){
                return letter;
            }
        }
        return letters[0];
    }
    public int lengthOfLastWord(String s) {
   if (s.length()==0){
       return 0;
   }
   if (s.lastIndexOf(" ")==s.length()-1){
       return 0;
   }
       String[] word=  s.split(" ");
   if (word.length==0){
       return 0;
   }else {
       return word[word.length-1].length();
   }

    }

    public int[] kWeakestRows(int[][] mat, int k) {
     int row=mat.length;
     int[] result=new int[row];
     int []  re=new int[k];
        int total=0;
        for (int i = 0; i < row; i++) {
            for (int j : mat[i]) {
                total=total+j;
            }

            result[i]=total*1000+i;
            total=0;
        }
      Arrays.sort(result);
        for (int i = 0; i < re.length; i++) {
            re[i]=result[i]%1000;
        }
        return re;
    }

    public TreeNode mirrorTree(TreeNode root) {
       if (root==null){
           return null;
       }
       if (root.right==null&&root.left==null) {
           return root;
       }
       TreeNode temp=null;
           temp=root.left;
           root.left=root.right;
           root.right=temp;


       if (root.left!=null){
           mirrorTree(root.left);
       }
       if (root.right!=null){
           mirrorTree(root.right);
       }

       return root;
    }
  List<Integer> list=new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
       mid(root);
       return buildBST(list,0,list.size()-1);
    }

    public TreeNode buildBST(List<Integer> list,int start,int end) {
        if (start>end){
            return null;
        }
        int mid =start+((start+end)>>1);
        TreeNode root =new TreeNode(list.get(mid));
       root.left= buildBST(list,0,mid-1);
        root.right=buildBST(list, mid+1, end);

        return root;
    }

    public void mid(TreeNode root){
        if (root!=null){
            mid(root.left) ;
            list.add(root.val);
            mid(root.right);
        }
    }


//    public List<String> readBinaryWatch(int num) {
//        List<String> result=new ArrayList<>();
//        for (int i = 0; i <12 ; i++) {
//            for (int j = 0; j <60 ; j++) {
//                if (count(i)+count(j)=num){
//                    result.add(i+":"+(j<10 ? "0"+j:j));
//                }
//            }
//        }
//        return result;
//    }

    public int count(int n){
        int count=0;
        while (n!=0){
            n=n&(n-1);
            count++;
        }
        return count;
    }

    public List<Integer> countSmaller(int[] nums) {
       int len=nums.length;
        List<Integer> result=new ArrayList<>();
       int num=0;
        for (int i = 0; i < len; i++) {
            for (int i1 = i; i1 < len; i1++) {
                if (nums[i1]<nums[i]){
                    num++;
                }
            }
            result.add(num);
            num=0;
        }
        return result;
    }

    public int cuttingRope(int n) {
       if (n==2){
           return 1;
       }
       if (n==3){
           return 2;
       }
       int result=1;
       int num=n/3;
      if (n%3==2){
          return (int) (Math.pow(3,num-1))*4;
       }else {
          return (int) Math.pow(3,num);
      }
    }
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
      int times=minutesToTest/minutesToDie;
      int temp=buckets/times;
      int n=temp,num=0;
      while (n!=0){
          n=n/2;
          num++;
      }
      if (Math.pow(2,num)*times>=buckets){
          return num;
      }else {
          return num+1;
      }

    }

    public int[] sort(int[] a) {
        int len = a.length;
        final int MAX = 256;
        int[] c = new int[MAX];
        int[] b = new int[MAX];

        for (int i = 0; i < len; i++) {
            c[a[i]]++;
        }

        for (int i = 1; i < MAX; i++) {
            c[i] += c[i-1];
            System.out.println("    "+c[i]);
        }

        for (int i = len - 1; i >= 0; i--) {
            b[c[a[i]] - 1] = a[i];
            System.out.println("  "+(c[a[i]] - 1)+"    "+a[i]);//2,3,1,2,4,9,6,5,7
            c[a[i]]--;
        }

        for (int i = 0; i < len; i++) {
            a[i] = b[i];
        }
        return a;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> a1=new ArrayList<>();
        List<Integer> a2=new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        mid(root1,a1);
        mid(root2,a2);
        a1.size()
        int i=0,j=0;
        while (i<a1.size()||j<a2.size()){
            if (a1.get(i)>=a2.get(j)){

                result.add(a2.get(j));
                j++;
            }else {

                result.add(a1.get(i));
                i++;
            }
        }

        while (i!=a1.size()){
            result.add(a1.get(i));
            i++;
        }

        while (j!=a2.size()){
            result.add(a2.get(j));
            j++;
        }

        return result;
    }

    public List<Integer> mid(TreeNode root ,List<Integer> a){
        if (root==null){
            return null;
        }
        if (root.left!=null){
            mid(root.left,a);
        }
        a.add(root.val);
        if (root.right!=null){
            mid(root.right,a);
        }

   return a;
    }
    public class ListNode {
      int val;
      ListNode next;
     ListNode(int x) { val = x; }
 }
    public boolean isPalindrome(ListNode head) {
      ListNode slow=head;
      ListNode fast=head;
      while (slow!=null&&fast!=null){
          slow=slow.next;
          fast=fast.next.next;
      }
      ListNode pre =null;
      while (slow!=null){
          ListNode curr=slow.next;
          curr=pre;
          pre=slow;
          slow=curr;
      }

      ListNode content=head;
      while (pre !=null){
          if (pre.val!=content.val){
              return false;
          }
          pre=pre.next;
          content=content.next;
      }

      return true;
    }

    public void setZeroes(int[][] matrix) {
      int[][] matriy=new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[0].length; i1++) {
                if (matrix[i][i1]==0){
                    matriy[i][i1]=1;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[0].length; i1++) {
                if (matriy[i][i1]==1){
                    for (int i2 = 0; i2 < matrix.length; i2++) {
                        matrix[i2][i1]=0;
                    }
                    for (int i2 = 0; i2 < matrix[i1].length; i2++) {
                        matrix[i][i2]=0;
                    }
                }
            }
        }
    }
    public int[] shortestToChar(String S, char C) {
         int len= S.length();
         int[] res=new int[len];
         int pre=Integer.MIN_VALUE/2;
        for (int i = 0; i < len; i++) {
            if (S.charAt(i)==C) pre =i;
            res[i]=i-pre;
        }
        pre =Integer.MAX_VALUE/2;
        for (int i = len-1; i >=0; i--) {
            if (S.charAt(i)==C) pre=i;
            res[i]=Math.min(pre-i,res[i]);
        }
        return res;
    }
    public int minMutation(String start, String end, String[] bank) {
        boolean isCan=false;
        for (int i = 0; i < bank.length; i++) {
            if (bank[i].equals(end)){
                isCan=true;
                break;
            }
        }
        if (!isCan){
            return  -1;
        }
        int count=0;
        isCan=false;
     char[] sp= start.toCharArray();
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i)!=end.charAt(i)){
                sp[i]=end.charAt(i);
                for (int j = 0; j < bank.length; j++) {
                    System.out.println("-111111111111"+String.valueOf(sp));

                    if (bank[i].equals(sp.toString())){
                        count++;
                        isCan=true;
                        break;
                    }
                }
                if (!isCan){
                    return -1;
                }
            }
        }
        return count;
        if (current.equals(end))
            minStepCount = Math.min(stepCount, minStepCount);
        for (String str: bank) {
            int diff = 0;
            for (int i = 0; i < str.length(); i++){
                if (current.charAt(i) != str.charAt(i))
                    if (++diff > 1) break;
            }

            if (diff == 1 && !step.contains(str)) {
                step.add(str);
                dfs(step, stepCount + 1, str, end, bank);
                step.remove(str);
            }
        }
    }

    private void dfs (HashSet<String> step, int stepCount,
                      String current, String end, String[] bank) {

        if (current.equals(end)){
            minStepCount = Math.min(stepCount, minStepCount);
            return;
        }
        for (String s : bank) {
            int count=0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i)!=current.charAt(i)){
                    count++;
                    if (count>1){
                        break;
                    }
                }
            }

            if (count==1&&!step.contains(s)){
                step.add(s);
                dfs(step, stepCount+1, s, end, bank);
                step.remove(s);
            }
            int minStepCount = Integer.MAX_VALUE;
        }
    }
    public int maxWidthRamp(int[] A) {
     int left=0,right=A.length-1;
      int ans=0;
     while (left<right){
         if (A[left] > A[right]) {
             left++;
         }else {
             ans=right-left;
         }
         if (A[left])
     }
     return right-left;
    }
 //123456780    221
 public int maxWidthRamp(int[] A) {

     int len=A.length;
     Integer [] b=new Integer[len];
     for (int i = 0; i < b.length; i++) {
         b[i]=i;
     }


     Arrays.sort(b, new Comparator<Integer>() {
         @Override
         public int compare(Integer i, Integer j) {
             return ((Integer) A[i]).compareTo(A[j]);
         }
     });

     int ans = 0;
     int m = len;
     for (int i: b) {
      ans=Math.max(ans,i-m);
      m=Math.min(i,m);
     }

     return ans;
 }
    public int getDecimalValue(ListNode head) {
     ListNode cur=head,next=head.next;
     if (head==null){
         return -1;
     }
        ListNode temp=null;
        while (next!=null){
         cur.next=temp;
         temp=next.next;
         next.next=cur;
         cur=next;
         next=temp;
     }
        ListNode h=cur;
     int ans=0;
     int i=0;
        while (h!=null){
           ans+= h.val*Math.pow(2,i);
           h=h.next;
        }

        return ans;
    }

    public int findTargetSumWays(int[] nums, int S) {
    int[][] dp=new int[nums.length][2001];
    dp[0][nums[0]+1000]=1;
    dp[0][-nums[0]+1000]+=1;

    for (int i=1;i<=nums.length;i++){
        for (int j = -1000; j <=1000 ; j++) {
            if (dp[i-1][j+1000]>0){
                dp[i][j+1000+nums[i]]+=dp[i-1][j+1000];
                dp[i][j+1000-nums[i]]+=dp[i-1][j+1000];
            }
        }
    }
      return S>1000 ?0:dp[nums.length-1][S+1000];
    }
    public int longestOnes(int[] A, int K) {
     int l=0,ans=0;
        int i = 0;
        for (; i <A.length ; i++) {
            if (A[i]==0){
                if (K>0){
                    K--;
                }else {
                    ans=Math.max(ans,i-l);
                    while (A[l++]==1);
                }
            }
        }

        return Math.max(ans,i-l);
    }
}