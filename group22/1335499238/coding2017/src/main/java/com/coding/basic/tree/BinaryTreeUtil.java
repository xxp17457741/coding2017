package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.stack.Stack;

public class BinaryTreeUtil {
	/**
	 * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderVisit(BinaryTreeNode<?> root) {
		List<T> result = new ArrayList<T>();
		preOrde(root, result);
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的中遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderVisit(BinaryTreeNode<?> root) {
		List<T> result = new ArrayList<T>();
		inOrder(root, result);
		return result;
	}

	/**
	 * 用递归的方式实现对二叉树的后遍历
	 * 
	 * @param root
	 * @return
	 */
	public static <T> List<T> postOrderVisit(BinaryTreeNode<?> root) {
		List<T> result = new ArrayList<T>();
		postOrder(root, result);
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的前序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<?> root) {
		List<T> result = new ArrayList<T>();		
		Stack s = new Stack();
		s.push(root);
		while(!s.isEmpty()) {
			BinaryTreeNode bt = (BinaryTreeNode)s.pop();
			result.add((T) bt.data);
			if(bt.right != null) s.push(bt.right);
			if(bt.left != null) s.push(bt.left);
		}
		return result;
	}
	/**
	 * 用非递归的方式实现对二叉树的中序遍历
	 * @param root
	 * @return
	 */
	public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<?> root) {
		List<T> result = new ArrayList<T>();
		Stack s = new Stack();
		BinaryTreeNode bt = root;
		while(bt!=null || !s.isEmpty()) {
			if(bt != null) {
				s.push(bt);
				bt = bt.left;
			}else {
				bt = (BinaryTreeNode)s.pop();
				result.add((T) bt.data);
				bt = bt.right;
		    }
		}
		return result;
	}
	
	private static <T> void preOrde(BinaryTreeNode<?> binaryTreeNode,List<T> result){
		result.add((T) binaryTreeNode.data);
 		if(binaryTreeNode.left != null){
 			preOrde(binaryTreeNode.left, result);
 		}
 		if(binaryTreeNode.right != null){
 			preOrde(binaryTreeNode.right, result);
 		}
 	}
	
	private static <T> void inOrder(BinaryTreeNode<?> binaryTreeNode,List<T> result){
 		if(binaryTreeNode.left != null){
 			inOrder(binaryTreeNode.left, result);
 		}
 		result.add((T) binaryTreeNode.data);
 		if(binaryTreeNode.right != null){
 			inOrder(binaryTreeNode.right, result);
 		}
 	}
	
	private static <T> void postOrder(BinaryTreeNode<?> binaryTreeNode,List<T> result){
 		if(binaryTreeNode.left != null){
 			postOrder(binaryTreeNode.left, result);
 		}
 		if(binaryTreeNode.right != null){
 			postOrder(binaryTreeNode.right, result);
 		}
 		result.add((T) binaryTreeNode.data);
 	}
}
