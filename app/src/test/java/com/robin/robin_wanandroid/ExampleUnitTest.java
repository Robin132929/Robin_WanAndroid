package com.robin.robin_wanandroid;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.HashMap;

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
}