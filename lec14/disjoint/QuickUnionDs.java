package disjoint;

import java.util.Arrays;

/**
 * problem: How use one element to represent the whole set?
 *
 * solve: use a tree. the root element represent the whole set,
 * and other elements point to the root element.
 *
 * detail1: should other elements point to the root elements? no
 * If assume that point to the root, when union two set, we still need to change
 * whole elements in the set(root element will change in union operation).
 * So, we should let elements just point to their parent element in the tree.
 * (parent element will not change in union operation)
 *
 * detail2: how can we find root element.(In other word, what value should be
 * stored in root element). just -1 for now
 *
 * good: quick union
 * bad: the runtime of union and find are all related to the height of tree, how to decrease the
 * height of tree?
 */
public class QuickUnionDs extends ArrayDs implements DisjointSet {
    public static int ROOT_VALUE=-1;
    public QuickUnionDs(int n){
        ds = new int[n];
        Arrays.fill(ds, ROOT_VALUE);
    }

    protected int findRoot(int p){
        validate(p);
        int id = p;
        while(ds[id]!=ROOT_VALUE){
            id = ds[id];
        }
        return id;
    }


    @Override
    public void connect(int p, int q) {
        validate(p);
        validate(q);
        ds[findRoot(p)]=findRoot(q);
    }

    @Override
    public boolean isConnected(int p, int q) {
        validate(p);
        validate(q);
        return findRoot(p)==findRoot(q);
    }
}
