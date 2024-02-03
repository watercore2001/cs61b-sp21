package deque;

public class Node<Item> {
    public Node<Item> preNode_;
    public Item item_;
    public Node<Item> postNode_;

    public Node(Node<Item> preNode, Item item, Node<Item> postNode){
        preNode_ = preNode;
        item_ = item;
        postNode_ = postNode;
    }
    public void SetPreNode(Node<Item> preNode){
        preNode_ = preNode;
        preNode_.postNode_ = this;
    }
    public void SetPostNode(Node<Item> postNode){
        postNode_ = postNode;
        postNode_.preNode_ = this;
    }
}
