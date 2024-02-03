package deque;

import java.util.Iterator;

/** Invariant
 * the addFirst Item will be added in position modLength(nextFirst_).
 * the addLast Item will be added in position modLength(nextLast_).
 * nextLast_ and nextFirst_ can not in [0, length-1]
 * normally, the size of array = (nextLast_ - nextFirst + 1) (+ length if less than 0)
 * when nextFirst_ equals nextFirst, there is only one empty pos in array.
 * @param <Item>
 */
public class ArrayDeque<Item> implements Deque<Item>{
    private int defaultLength_=8;
    private Item[] array_;
    private int nextFirst_;
    private int nextLast_;

    public ArrayDeque(){
        array_ = (Item[])new Object[defaultLength_];
        nextFirst_ = defaultLength_ / 2;
        nextLast_ = nextFirst_ + 1;
    }

    private int modLength(int x){return Math.floorMod(x, array_.length);}

    private void resize(int newLength){
        Item[] newArray = (Item[])new Object[newLength];
        int size = size();
        for(int i=0; i<size; i++){
            newArray[i] = get(i);
        }
        array_ = newArray;
        // bad practice: do not call size() when you change nextLast and nextFirst
        nextLast_ = size;
        nextFirst_ = -1;
    }

    @Override
    public void addFirst(Item item) {
        if(size()==array_.length){
            resize(size()*2);
        }
        array_[modLength(nextFirst_)] = item;
        nextFirst_--;
    }

    @Override
    public void addLast(Item item) {
        if(size()==array_.length){
            resize(size()*2);
        }
        array_[modLength(nextLast_)] = item;;
        nextLast_++;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public int size() {
        int size = nextLast_ - nextFirst_ - 1;
        assert size <= array_.length;
        return size;
    }

    @Override
    public int printDeque() {
        for(int i=0; i<size(); i++){
            System.out.println(get(i));
        }
        return 0;
    }

    @Override
    public Item removeFirst() {
        if(size()==0){
            return null;
        }
        int rel_pos = modLength(nextFirst_+1);
        Item first_item = array_[rel_pos];
        array_[rel_pos] = null;
        nextFirst_++;
        return first_item;
    }

    @Override
    public Item removeLast() {
        if(size()==0){
            return null;
        }
        int rel_pos = modLength(nextLast_-1);
        Item last_item = array_[rel_pos];
        // clear last item
        array_[rel_pos] = null;
        nextLast_--;
        return last_item;
    }

    @Override
    public Item get(int index) {
        int real_pos = modLength(nextFirst_ + 1 + index);
        return array_[real_pos];
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
