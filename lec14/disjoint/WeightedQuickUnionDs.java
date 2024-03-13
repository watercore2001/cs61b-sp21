package disjoint;

/**
 * ROOT_VALUE is not always -1, the value of root element
 * is the weight of the tree
 *
 * good: the height of tree is assured that less than log2N
 * bad: if the operation number is large, log2N is still too high
 */
public class WeightedQuickUnionDs extends QuickUnionDs implements DisjointSet{

    public WeightedQuickUnionDs(int n) {
        super(n);
    }

    protected int findWeight(int p){
        return -ds[findRoot(p)];
    }

    @Override
    public int findRoot(int p){
        validate(p);
        int id = p;
        while(ds[id]>=0){
            id = ds[id];
        }
        return id;
    }

    @Override
    public void connect(int p, int q) {
        validate(p);
        validate(q);
        int p_root = findRoot(p);
        int q_root = findRoot(q);
        if (p_root==q_root){
            return;
        }
        if (findWeight(p)<=findWeight(q)){
            ds[q_root] += ds[p_root];
            ds[p_root] = q_root;
        }else {
            ds[p_root] += ds[q_root];
            ds[q_root] = p_root;
        }
    }

}
