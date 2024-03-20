package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<Key extends Comparable<Key>, Value> implements Map61B<Key, Value> {
    private BST<Key, Value> bst;
    public BSTMap(){
        bst = new BST<Key, Value>();
    }

    @Override
    public void clear() {
        bst.clear();
    }

    @Override
    public boolean containsKey(Key key) {

        return bst.get(key)!=null;
    }

    @Override
    public Value get(Key key) {

        return bst.get(key);
    }

    @Override
    public int size() {
        return bst.size();
    }

    @Override
    public void put(Key key, Value value) {
        bst.put(key, value);
    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

    @Override
    public Value remove(Key key) {
        return null;
    }

    @Override
    public Value remove(Key key, Value value) {
        return null;
    }

    @Override
    public Iterator<Key> iterator() {
        return null;
    }


}
