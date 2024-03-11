package disjoint;

public abstract class ArrayDs {
    protected int [] ds;

    protected void validate(int p){
        if (p<0 || p>=ds.length){
            throw new IllegalArgumentException("Invalid index" + p);
        }
    }
}
