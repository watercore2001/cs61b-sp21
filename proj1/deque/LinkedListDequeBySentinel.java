package deque;

import java.util.Iterator;


/** Invariant
 * 1. tow sentinel node's items, first sentinel node's firstNode and last sentinel's preNode, are always null
 * 2. when add one node in the first, first sentinel's postNode should point to this new node
 * 3. when add one node in the last, last sentinel's lastNode should point to this new node
 *
 * @param <Item>
 */
public class LinkedListDequeBySentinel<Item> implements Deque<Item> {
    public static class DeNode<Item> {
        public DeNode<Item> preNode_;
        public Item item_;
        public DeNode<Item> postNode_;

        public DeNode(DeNode<Item> preNode,Item item, DeNode<Item> postNode){
            preNode_ = preNode;
            item_ = item;
            postNode_ = postNode;
        }
        public void SetPreNode(DeNode<Item> preNode){
            preNode_ = preNode;
            preNode_.postNode_ = this;
        }
        public void SetPostNode(DeNode<Item> postNode){
            postNode_ = postNode;
            postNode_.preNode_ = this;
        }
    }

    int size_;
    DeNode<Item> firstSentinel_;
    DeNode<Item> lastSentinel_;

    public LinkedListDequeBySentinel(){
        size_ = 0;
        firstSentinel_ = new DeNode<>(null, null, null);
        lastSentinel_ = new DeNode<>(null, null, null);
        firstSentinel_.SetPostNode(lastSentinel_);
    }

    private void AddNode(DeNode<Item> preNode, Item item, DeNode<Item> postNode){
        DeNode<Item> newNode = new DeNode<Item>(null, item, null);
        newNode.SetPreNode(preNode);
        newNode.SetPostNode(postNode);
    }

    @Override
    public void addFirst(Item item) {
        AddNode(firstSentinel_, item, firstSentinel_.postNode_);
        size_ += 1;
    }

    @Override
    public void addLast(Item item) {
        AddNode(lastSentinel_.preNode_, item, lastSentinel_);
        size_ += 1;
    }

    @Override
    public boolean isEmpty() {
        return size_ == 0;
    }

    @Override
    public int size() {
        return size_;
    }

    @Override
    public int printDeque() {
        for (DeNode<Item> node = firstSentinel_.postNode_; node.postNode_ != null; node = node.postNode_){
            System.out.println(node.item_);
        }
        return 0;
    }

    @Override
    public Item removeFirst() {
        if(size()==0){
            return null;
        }
        DeNode<Item> first_node = firstSentinel_.postNode_;
        firstSentinel_.SetPostNode(firstSentinel_.postNode_.postNode_);
        size_ -= 1;
        return first_node.item_;
    }

    @Override
    public Item removeLast() {
        if(size()==0){
            return null;
        }
        DeNode<Item> last_node = lastSentinel_.preNode_;
        lastSentinel_.SetPreNode(lastSentinel_.preNode_.preNode_);
        size_ -= 1;
        return last_node.item_;
    }

    @Override
    public Item get(int index) {
        assert index >= 0 && index < size();
        DeNode<Item> current_node = firstSentinel_.postNode_;
        for (int i=0; i<index; i++){
            current_node= current_node.postNode_;
        }
        return current_node.item_;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
