package com.coding.basic.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		return findMin(root).data;
	}
	
	public T findMax(){
		return findMax(root).data;
	}
	
	public int height() {
		return getMaxHeight(root);
	}
	
	public int size() {
		List<Object> list = BinaryTreeUtil.inOrderVisit(root);
		return list.size();
	}
	
	public void remove(T e){
		remove(e, root);
	}
	
	public List<T> levelVisit(){
		if (root == null){
			return null;
		}
		ArrayDeque<BinaryTreeNode<T>> queue = new ArrayDeque<BinaryTreeNode<T>>();
		List<T> list = new ArrayList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BinaryTreeNode<T> temp = queue.poll();
			list.add( temp.data);
			if (temp.left != null){
				queue.add(temp.left);
			}
			if (temp.right != null){
				queue.add(temp.right);
			}
		}
		return list;
	}
	
	public boolean isValid(){
		if (root == null){
			return false;
		}
		List<T> inOrderVisit = BinaryTreeUtil.inOrderVisit(root);
		int i = 0;
		int size = inOrderVisit.size() - 1;
		while(i < size){
			if(inOrderVisit.get(0).compareTo( inOrderVisit.get(i+1)) > 0){
				return false;
			}
			i++;
		}
		return true;
	}
	
	public T getLowestCommonAncestor(T n1, T n2){
		return lowestCommonParentNode(n1, n2, root).data;
	}
	
	/**
	 * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , n 为
	 * 该二叉查找树中的某一节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2){
		List<T> list = new ArrayList<>();
		List<T> inOrderVisit = BinaryTreeUtil.inOrderVisit(root);
		for (T t : inOrderVisit) {
			if(n1.compareTo(t) <= 0 && n2.compareTo(t) >= 0){
				list.add(t);
			}
		}
		return list;
	}
	
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node){
		if(node == null){
			return null;
		} else if(node.left == null){
			return node;
		}else{
			return findMin(node.left);
		}
	}
	
	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> node){
		if(node == null){
			return null;
		} else if(node.right == null){
			return node;
		}else{
			return findMax(node.right);
		}
	}
	
	private int getMaxHeight(BinaryTreeNode<T> node){
		if (node == null){
            return 0;
		}else {
            int left = getMaxHeight(node.left);
            int right = getMaxHeight(node.right);
            return 1 + Math.max(left, right);
        }
	}
	
	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> node){
		if(node == null){
			return node;
		}
		int compareResult = e.compareTo(node.data);
		if(compareResult < 0){
			node.left = remove(e, node.left);
		} else if(compareResult > 0){
			node.right = remove(e, node.right);
		} else {
			if(node.left != null && node.right != null){
				node.data = findMin(node.right).data;
				node.right = remove((T) node.data, node.right);
			}else{
				node = (node.left != null) ? node.left : node.right;
			}
		}
		return node;
	}
	
	public BinaryTreeNode<T> lowestCommonParentNode(T n1, T n2, BinaryTreeNode<T> node){
		if(node == null){
			return null;
		}
		T data = node.data;
		int compareN1 = n1.compareTo(data);
		int compareN2 = n2.compareTo(data);
		if(compareN1 < 0 && compareN2 > 0){
			return node;
		}
		if(compareN1 < 0  && compareN2 < 0){//在左子树中查找最低公共祖先结点
			return lowestCommonParentNode(n1, n2, node.left);
		} else{			//在右子树中查找最低公共祖先结点
			return lowestCommonParentNode(n1, n2, node.right);
        }
	}
	
}

