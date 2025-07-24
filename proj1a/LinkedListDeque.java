class Node<T> {
    private Node<T> next,prev;
    private T value;

    public Node(Node<T> prev,Node<T> next,T value){
        this.next=next;
        this.prev=prev;
        this.value=value;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrev() {
        return prev;
    }
    public T getValue(){
        return value;
    }

    public void setNext(Node<T> next){
        this.next=next;
    }
    public void setPrev(Node<T> prev){
        this.prev=prev;
    }

}

public class LinkedListDeque<T> {
    int size;
    Node<T> head,tail;
    public LinkedListDeque(){
        head=tail=null;
        size=0;
        return;
    }
    public void addFirst(T item){
        Node<T> newNode=new Node<>(null,head,item);
        if(head!=null){
            head.setPrev(newNode);
        }
        else
        {
            tail=newNode;
        }
        head=newNode;
        size+=1;
    }

    public void addLast(T item){
        Node<T> newNode=new Node<>(tail,null,item);
        if(tail!=null){
            tail.setNext(newNode);
        }
        else
        {
            head=newNode;
        }
        tail=newNode;
        size+=1;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return this.size;
    }
    public void printDeque(){
        Node<T> now=head;
        while(now!=null){
            System.out.print(now.getValue());
            now=now.getNext();
        }
        System.out.println("");
    }

    public T removeFirst(){
        assert size>0;
        T res=head.getValue();
        head=head.getNext();
        size--;
        if(size==0){
            tail=null;
        }
        return res;
    }
    public T removeLast(){
        assert size>0;
        T res=tail.getValue();
        tail=tail.getPrev();
        size--;
        if(size==0){
            head=null;
        }
        return res;
    }
    public T get(int index){
        Node<T> now=head;
        for(int i=1;i<=index;i++)now=now.getNext();
        return now.getValue();
    }
    private T doGetRecursive(Node<T> now,int index){
        if(index==0||now==null)return now.getValue();
        return doGetRecursive(now.getNext(),index-1);
    }
    public T getRecursive(int index){
        return doGetRecursive(head,index);
    }
//    public static void main(String[] args){
//        LinkedListDeque deque=new LinkedListDeque();
//        deque.addFirst(0);
//        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        int res= (int) deque.removeLast();
//        System.out.println(res);
//    }
}