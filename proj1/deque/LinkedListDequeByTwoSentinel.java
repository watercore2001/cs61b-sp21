package deque;

import java.util.Iterator;


/** Invariant
 * 1. tow sentinel node's items, first sentinel node's firstNode and last sentinel's preNode, are always null
 * 2. when add one node in the first, first sentinel's postNode should point to this new node
 * 3. when add one node in the last, last sentinel's lastNode should point to this new node
 *
 * @param <Item>
 */
public class LinkedListDequeByTwoSentinel<Item> implements Deque<Item> {
    int size_;
    Node<Item> firstSentinel_;
    Node<Item> lastSentinel_;

    public LinkedListDequeByTwoSentinel(){
        size_ = 0;
        firstSentinel_ = new Node<>(null, null, null);
        lastSentinel_ = new Node<>(null, null, null);
        firstSentinel_.SetPostNode(lastSentinel_);
    }

    private void AddNode(Node<Item> preNode, Item item, Node<Item> postNode){
        Node<Item> newNode = new Node<Item>(null, item, null);
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
        for (Node<Item> node = firstSentinel_.postNode_; node.postNode_ != null; node = node.postNode_){
            System.out.println(node.item_);
        }
        return 0;
    }

    @Override
    public Item removeFirst() {
        if(size()==0){
            return null;
        }
        Node<Item> first_node = firstSentinel_.postNode_;
        firstSentinel_.SetPostNode(firstSentinel_.postNode_.postNode_);
        size_ -= 1;
        return first_node.item_;
    }

    @Override
    public Item removeLast() {
        if(size()==0){
            return null;
        }
        Node<Item> last_node = lastSentinel_.preNode_;
        lastSentinel_.SetPreNode(lastSentinel_.preNode_.preNode_);
        size_ -= 1;
        return last_node.item_;
    }

    @Override
    public Item get(int index) {
        assert index >= 0 && index < size();
        Node<Item> current_node = firstSentinel_.postNode_;
        for (int i=0; i<index; i++){
            current_node= current_node.postNode_;
        }
        return current_node.item_;
    }

    public Item getRecursiveHelper(Node<Item> start, int index){
        if(index==0){
            return start.item_;
        }
        return getRecursiveHelper(start.postNode_, index-1);
    }

    public Item getRecursive(int index){
        assert index >= 0 && index < size();
        return getRecursiveHelper(firstSentinel_.postNode_, index);
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
