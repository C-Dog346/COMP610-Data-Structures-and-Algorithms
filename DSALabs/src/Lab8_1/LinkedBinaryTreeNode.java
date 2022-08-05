/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Lab8_1;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author callumclow
 */
public class LinkedBinaryTreeNode<E> implements MutableTreeNode {
    
    private E element;
    private MutableTreeNode parent;
    private MutableTreeNode left;
    private MutableTreeNode right;
    
    public LinkedBinaryTreeNode()
    {  this(null);
    }
    
    public LinkedBinaryTreeNode(E element) {
        this.element = element;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        if (child == null)
            throw new IllegalArgumentException("Null child my G");
        child.removeFromParent();;
        switch (index) {
            case 0:
                this.left = child;
                break;
            case 1:
                this.right = child;
                break;
            default:
                throw new IllegalArgumentException("Index out of bounds");
        }
        child.setParent(this);
    }

    @Override
    public void remove(int index) {
        MutableTreeNode child = null;
        
        switch (index) {
            case 0:
                child = this.left;
                break;
            case 1:
                child = this.right;
                break;
            default:
                throw new IllegalArgumentException("Index out of bounds");
        }
        
        if (child!=null);
            remove(child);
    }

    @Override
    public void remove(MutableTreeNode node) {
        if (node.equals(this.left)) { 
            
            node.setParent(null);
        }
        if (node.equals(this.right)) {
            node.setParent(null);
        }
        else
            throw new IllegalArgumentException("Index out of bounds");
    }

    @Override
    public void setUserObject(Object object) {
        this.element = (E)object;
    }
    
    public Object getUserObject() {
        return this.element;
    }

    @Override
    public void removeFromParent() {
        if (this.parent!=null) {  
            parent.remove(this);
            this.parent = null;
        }
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        if (this.getAllowsChildren())
            this.parent = newParent;
        else
            throw new IllegalArgumentException("Parent node already has two children");
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        switch (childIndex) {
            case 0:
                return this.left;
            case 1:
                return this.right;
            default:
                throw new IllegalArgumentException("Index out of bounds");
        }
    }

    @Override
    public int getChildCount() {
        int count = 0;
        
        if (this.left != null)
            count++;
        if (this.right != null)
            count++;
        
        return count;
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (node.equals(this.left))
            return 0;
        if (node.equals(this.right))
            return 1;
        else
            throw new IllegalArgumentException("Index out of bounds");
    }

    @Override
    public boolean getAllowsChildren() {
        return this.getChildCount() < 2;
    }

    @Override
    public boolean isLeaf() {
        return (getChildCount()==0);
    }

    @Override
    public Enumeration<MutableTreeNode> children() {
        LinkedList<MutableTreeNode> leftNRight = new LinkedList();
        
        if (this.left != null)
            leftNRight.add(this.left);
        if (this.right != null)
            leftNRight.add(this.right);
        
        return (Enumeration<MutableTreeNode>)(new Enumerator(leftNRight.iterator()));
    }
    
    class Enumerator<E> implements Enumeration<E>
    {
        private Iterator<E> iterator;

        public Enumerator(Iterator<E> iterator)
        {  this.iterator = iterator;
        }

        public boolean hasMoreElements()
        {  return iterator.hasNext();
        }

        public E nextElement()
        {  return iterator.next();
        }
    }
    
    public static void main(String[] args) {
        LinkedBinaryTreeNode root = new LinkedBinaryTreeNode();
        root.setUserObject(0);
        LinkedBinaryTreeNode node1 = new LinkedBinaryTreeNode(1);
        LinkedBinaryTreeNode node2 = new LinkedBinaryTreeNode(2);
        LinkedBinaryTreeNode node3 = new LinkedBinaryTreeNode(3);
        LinkedBinaryTreeNode node4 = new LinkedBinaryTreeNode(4);
        LinkedBinaryTreeNode node5 = new LinkedBinaryTreeNode(5);
        
        root.insert(node1, 0);
        root.insert(node2, 1);
        node1.insert(node3, 0);
        node1.insert(node4, 1);
        node2.insert(node5, 0);
        
        System.out.println(((LinkedBinaryTreeNode) root.getChildAt(0)).getUserObject());
        System.out.println(((LinkedBinaryTreeNode) root.getChildAt(1)).getUserObject());
        System.out.println(((LinkedBinaryTreeNode) node1.getChildAt(0)).getUserObject());
        System.out.println(((LinkedBinaryTreeNode) node1.getChildAt(1)).getUserObject());
        System.out.println(((LinkedBinaryTreeNode) node2.getChildAt(0)).getUserObject());
    }

}
