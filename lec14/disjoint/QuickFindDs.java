package disjoint;

/**
 * solve problem find: To implement QuickFind, we need data structure like
 * dict {element: set}, built-in array is a dict indeed!
 *
 * good: QuickFind
 *
 * bad: need iterate whole array when union two set，
 * can we just change one element's value to represent the whole set's elements?
 */
public class QuickFindDs extends ArrayDs implements DisjointSet {
    public QuickFindDs(int n){
        ds = new int[n];
        for(int i=0; i<n; i++) {
            ds[i] = i;
        }
    }

    @Override
    public void connect(int p, int q) {
        validate(p);
        validate(q);

        int s1 = ds[p];
        int s2 = ds[q];
        // check special case for speed up
        if (s1==s2) return;

        for (int i=0; i<ds.length; i++){
            if(ds[i]==s1){
                ds[i]=s2;
            }
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        validate(p);
        validate(q);
        return ds[p]==ds[q];
    }
}
