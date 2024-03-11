package disjoint;

/**
 * problem find: How to find which set the number element belongs to
 * problem union: How to union all elements in one set to another set
 */
public interface DisjointSet {
    /**
     * union p into q
     * @param p
     * @param q
     */
    void connect(int p, int q);
    boolean isConnected(int p, int q);
}
