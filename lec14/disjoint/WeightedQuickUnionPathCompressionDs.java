package disjoint;

/**
 * when do the union operation, try to set all elements in the smaller tree
 * point to the larger tree directly.
 * But it's expensive to iterate one tree (O(N)), so we can just set elements in the path from
 * p to root, points to the other root directly.
 */
public class WeightedQuickUnionPathCompressionDs extends WeightedQuickUnionDs implements DisjointSet{
    public WeightedQuickUnionPathCompressionDs(int n){
        super(n);
    }

    private void setPathToRoot(int p, int root){
        validate(p);
        int id = p;
        while(ds[id]>=0){
            int new_id = ds[id];
            ds[id] = root;
            id = new_id;
        }
    }

    @Override
    public void connect(int p, int q) {
        // connect first
        super.connect(p, q);

        setPathToRoot(p, findRoot(p));
        setPathToRoot(q, findRoot(q));
    }
}
