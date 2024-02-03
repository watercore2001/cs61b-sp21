package deque;

import java.util.Iterator;

/** Invariant
 * sentinel's preNode always points to the last node
 * sentinel's postNode always points to the first node
 * @param <Item>
 */
public class LinkedListDequeByCircularSentinel<Item> implements Deque<Item> {
    int size_;
    Node<Item> sentinel_;

    public LinkedListDequeByCircularSentinel(){
        size_ = 0;
        sentinel_ = new Node<>(null, null, null);
        sentinel_.preNode_ = sentinel_;
        sentinel_.postNode_ = sentinel_;
    }

    private void AddNode(Node<Item> preNode, Item item, Node<Item> postNode){
        Node<Item> newNode = new Node<Item>(null, item, null);
        newNode.SetPreNode(preNode);
        newNode.SetPostNode(postNode);
    }

    @Override
    public void addFirst(Item item) {
        AddNode(sentinel_, item, sentinel_.postNode_);
        size_++;
    }

    @Override
    public void addLast(Item item) {
        AddNode(sentinel_, item, sentinel_.preNode_);
        size_++;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public int size() {
        return size_;
    }

    @Override
    public int printDeque() {
        for (Node<Item> node = sentinel_.postNode_; node.item_ != null; node = node.postNode_){
            System.out.println(node.item_);
        }
        return 0;
    }

    @Override
    public Item removeFirst() {
        if(size()==0){
            return null;
        }
        Node<Item> first_node = sentinel_.postNode_;
        sentinel_.SetPostNode(sentinel_.postNode_.postNode_);
        size_ -= 1;
        return first_node.item_;
    }

    @Override
    public Item removeLast() {
        if(size()==0){
            return null;
        }
        Node<Item> last_node = sentinel_.preNode_;
        sentinel_.SetPreNode(sentinel_.preNode_.preNode_);
        size_ -= 1;
        return last_node.item_;
    }

    @Override
    public Item get(int index) {
        assert index >= 0 && index < size();
        Node<Item> current_node = sentinel_.postNode_;
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
