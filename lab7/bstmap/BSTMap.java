package bstmap;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.HashSet;

public class BSTMap<Key extends Comparable<Key>, Value> implements Map61B<Key, Value> {
    private Node root;
    private int size;

    @Override
    public Iterator<Key> iterator() {
        return new BSTIterator();
    }

    private class Node{
        private Key key;
        private Value value;
        private Node leftNode;
        private Node rightNode;

        public Node(Key key, Value value, Node leftNode, Node rightNode){
            this.key = key;
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    private class BSTIterator implements Iterator<Key>{
        private final Stack<Node> nodes;

        public BSTIterator(){
            nodes = new Stack<>();
            addLeftLine(root);
        }

        private void addLeftLine(Node node){
            if(node!=null){
                nodes.add(node);
                addLeftLine(node.leftNode);
            }
        }

        @Override
        public boolean hasNext() {
            return !nodes.empty();
        }

        /**
         * When you iter a node, means you have iter all of it's left tree,
         * So It's time to iter it's right tree
         * @return current node's key
         */
        @Override
        public Key next() {
            Node node = nodes.pop();
            addLeftLine(node.rightNode);
            return node.key;
        }
    }

    public BSTMap(){size=0;}

    /**
     *
     * @param root maybe null
     * @param key is assumed that not be null
     * @return the Node associated to the key, return null if key is not in the tree
     */
    private Node getHelper(Node root, Key key){
        if (root==null) return null;
        // important: don't use root.key==key,
        // == operation is not defined in Comparable Interface
        int cmp = key.compareTo(root.key);
        if (cmp<0) return getHelper(root.leftNode, key);
        else if(cmp>0) return getHelper(root.rightNode, key);
        else return root;
    }

    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("Calls get() with a null key");
        Node node = getHelper(root, key);
        if (node==null) return null;
        else return node.value;
    }

    private int sizeHelper(Node node){
        if (node == null) {
            return 0;
        }
        return sizeHelper(node.leftNode) + sizeHelper(node.rightNode) + 1;
    }

    public int size(){
        // elegant recursion version
        assert size==sizeHelper(root);
        return size;
    }


    /** Insert the key-value in the tree. Overwriting the old value with the new value if the tree
     * already contains the key
     *
     * @param root maybe null
     * @param key is assumed that not be null
     * @param value is assumed that not be null
     * @return return the new root after add this node.
     * case1: if root is null, create a new node and return
     * case2: else, just return current root after put
     */
    private Node putHelper(Node root, Key key, Value value){
        if(root==null) {
            size++;
            return new Node(key, value, null, null);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.leftNode = putHelper(root.leftNode, key, value);
        else if(cmp >0) root.rightNode = putHelper(root.rightNode, key, value);
        else root.value = value;
        return root;
    }

    public void put(Key key, Value value){
        if (key == null)
            throw new IllegalArgumentException("Calls put() with a null key or value");
        // important: must refresh new root
        root = putHelper(root, key, value);
    }

    private void keySetHelper(Node node, Set<Key> set){
        if(node != null){
            keySetHelper(node.leftNode, set);
            keySetHelper(node.rightNode, set);
            set.add(node.key);
        }
    }

    @Override
    public Set<Key> keySet() {
        Set<Key> set = new HashSet<>();
        keySetHelper(root, set);
        return set;
    }

    private Node findMax(Node root){
        if(root==null) throw new IllegalArgumentException("Calls findMax() in a null tree");
        if(root.rightNode==null) return root;
        return findMax(root.rightNode);
    }

    /**
     *
     * @param root maybe null
     * @param key is assumed that not be null
     * @return return new root after delete this node.
     * case1: if key is not existed, return null
     */
    private Node removeHelper(Node root, Key key){
        if (root==null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp<0) root.leftNode = removeHelper(root.leftNode, key);
        else if(cmp>0) root.rightNode = removeHelper(root.rightNode, key);
        else{
            size--;
            if (root.leftNode==null) return root.rightNode;
            if (root.rightNode==null) return root.leftNode;
            Node maxNode = findMax(root.leftNode);
            root.key = maxNode.key;
            root.value = maxNode.value;
            root.leftNode = removeHelper(root.leftNode, maxNode.key);
        }
        return root;
    }

    @Override
    public Value remove(Key key) {
        return remove(key, get(key));
    }

    @Override
    public Value remove(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("Calls delete() with a null key");
        if (value.equals(get(key))){
            root = removeHelper(root, key);
            return value;
        }
        else{
            throw new InputMismatchException("Not match");
        }
    }

    public void clear(){
        root=null;
        size=0;
    }

    @Override
    public boolean containsKey(Key key) {
        return get(key)!=null;
    }


}
