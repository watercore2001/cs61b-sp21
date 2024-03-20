package bstmap;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

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

    public BST(){}

    /**
     *
     * @param root maybe null
     * @param key is assumed that not be null
     * @return the value associated to the key, return null if key is not in the tree
     */
    private Value getHelper(Node root, Key key){
        if (root==null) return null;
        // important: don't use root.key==key, == operation is not defined in comparable
        int cmp = key.compareTo(root.key);
        if (cmp<0) return getHelper(root.leftNode, key);
        else if(cmp>0) return getHelper(root.rightNode, key);
        else return root.value;
    }

    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return getHelper(root, key);
    }

    private int size(Node node){
        if (node == null) {
            return 0;
        }
        return size(node.leftNode) + size(node.rightNode) + 1;
    }

    public int size(){
        return size(root);
    }


    /** Insert the key-value in the tree. Overwriting the old value with the new value if the tree
     * already contains the key
     *
     * @param root maybe null
     * @param key is assumed that not be null
     * @param value is assumed that not be null
     * @return return the new root after add this node.
     * case1: if root is null, return the created new node,
     * case2: else, just return current root.
     */
    private Node putHelper(Node root, Key key, Value value){
        if(root==null) return new Node(key, value, null, null);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.leftNode = putHelper(root.leftNode, key, value);
        else if(cmp >0) root.rightNode = putHelper(root.rightNode, key, value);
        else root.value = value;
        return root;
    }

    public void put(Key key, Value value){
        if (key == null)
            throw new IllegalArgumentException("calls put() with a null key or value");
        // important: must refresh new root
        root = putHelper(root, key, value);
    }

    private Node findMax(Node root){
        if(root==null) throw new IllegalArgumentException("calls findMax() in a null tree");
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
    private Node deleteHelper(Node root, Key key){
        if (root==null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp<0) root.leftNode = deleteHelper(root.leftNode, key);
        else if(cmp>0) root.rightNode = deleteHelper(root.rightNode, key);
        else{
            if (root.leftNode==null) return root.rightNode;
            if (root.rightNode==null) return root.leftNode;
            Node maxNode = findMax(root.leftNode);
            root.key = maxNode.key;
            root.value = maxNode.value;
            root.leftNode = deleteHelper(root.leftNode, maxNode.key);
        }
        return root;
    }

    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        // important: must refresh new root
        root = deleteHelper(root, key);
    }

    public void clear(){
        root=null;
    }
}
