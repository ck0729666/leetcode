package com.ck.niuke;

import java.util.*;

public class TreeNodeDemo {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root!=null){
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty()||root!=null){
                if(root!=null){
                    stack.add(root);
                    root = root.left;
                }else {
                    root = stack.pop();
                    list.add(root.val);
                    root = root.right;
                }
            }
        }
        return list;
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root!=null){
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.add(root);
            while (!stack1.isEmpty()){
                root = stack1.pop();
                stack2.add(root);
                if(root.left!=null){
                    stack1.add(root.left);
                }
                if(root.right!=null){
                    stack1.add(root.right);
                }
            }
            while (!stack2.isEmpty()){
                list.add(stack2.pop().val);
            }
        }
        return list;
    }

    //前中后序递归遍历,输出放不同的位置，放1是前，放2是中，放3是后
    public static void inOrderRecur1(TreeNode head) {
        if(head==null){
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.val+" ");
        inOrderRecur(head.right);
    }

    //非递归前序
    public static void preOrderRecur(TreeNode head) {
        if(head!=null){
            Stack<TreeNode> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                if(head.right!=null){
                    stack.push(head.right);
                }
                if(head.left!=null){
                    stack.push(head.left);
                }
            }
        }
    }
    //中序，当前节点为空，从栈拿一个打印，当前去右边；不空，则压入栈，当前为左
    public static void inOrderRecur(TreeNode head) {
        if(head!=null){
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head!=null){
                if(head!=null){
                    stack.add(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.println(head.val+" ");
                    head = head.right;
                }
            }
        }
    }
    //后序
    public static void nextOrderRecur(TreeNode head) {
        if(head!=null){
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.add(head);
            while (!s1.isEmpty()){
                head = s1.pop();
                //把中右左放入s2中
                s2.add(head);
                if(head.left!=null){
                    s1.add(head.left);
                }
                if(head.right!=null){
                    s1.add(head.right);
                }
            }
            //打印出来顺序是：左右中
            for(TreeNode node : s2){
                System.out.println(node.val);
            }
        }
    }
    boolean res = true;
    public boolean isBalanced(TreeNode root) {
        isBan(root);
        return res;
    }
    public int isBan(TreeNode root){
        if(root==null){return 0;}
        int left = isBan(root.left)+1;
        int right = isBan(root.right)+1;
        if(Math.abs(left-right)>1){res=false;}
        return Math.max(left,right);
    }

    //判断是否搜索二叉树，非递归
    public boolean isValidBST(TreeNode root) {
        int pre = Integer.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        //stack.add(root);
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                root = stack.pop();

                if (pre > root.val) {
                    return false;
                }
                pre = root.val;
                root = root.right;
            }
        }
        return true;
    }

    //判断是否是完全二叉树，
    //层序遍历
    //判断1：任何一个节点有右孩子无左孩子时返回false
    //判断2：当发现一个节点的2孩子不全的时候，后面的必须全是叶节点
    //958
    public boolean isCompleteTree(TreeNode root) {
        if(root==null){
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean flag = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(root);
        while (!queue.isEmpty()){
            root = queue.poll();
            l = root.left;
            r = root.right;
            if((flag&&(l!=null||r!=null)) || (l==null&&r!=null)){
                return false;
            }
            if(l!=null){
                queue.offer(l);
            }
            if(r!=null){
                queue.offer(r);
            }else {
                flag = true;
            }
        }
        return true;
    }


    //层序遍历
    List<List<Integer>> levels = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){return levels;}
        helper(root, 0);
        return levels;
    }
    public void helper(TreeNode node, int level){
        if(levels.size()==level){
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(node.val);
        if(node.left!=null){
         helper(node.left, level+1);
        }
        if(node.right!=null){
         helper(node.right, level+1);
        }
    }
    //求完全二叉树的个数
    //思路：只看右子树
    // 1.若右子树到了最下面一层，则左边子树个数通过公式求出，然后递归右边
    // 2.若右子树最后一层没有节点，则计算右边子树个数，然后递归左边
    public int countNodes(TreeNode root) {
        if (root==null){
            return 0;
        }
        return bs(root,1,mostLeftLevel(root,1));
    }
    public int bs(TreeNode root, int level, int height){
        if(level==height){
            return 1;
        }
        if(mostLeftLevel(root.right,level+1)==height){
            return (1<<(height-level)) + bs(root.right,level+1,height);
        }else {
            return (1<<(height-level-1)) + bs(root.left,level+1,height);
        }
    }
    public int mostLeftLevel(TreeNode root, int level){
        while (root!=null){
            level++;
            root = root.left;
        }
        return level-1;
    }




























}


































class Demo{
    boolean flag;
    int num;
    public Demo(boolean flag, int num){
        this.flag = flag;
        this.num = num;
    }
}