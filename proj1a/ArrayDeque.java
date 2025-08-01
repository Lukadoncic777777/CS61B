public class ArrayDeque<T> {
    private int size,head,tail,nowSize;
    private static int beginSize=8;
    private T[] items;
    public ArrayDeque(){
        this.size=0;
        this.head=0;
        this.tail=0;
        this.items=(T[]) new Object[beginSize];
        this.nowSize=beginSize;
    }

    private void resize(int newSize){
        T[] newItems=(T[]) new Object[newSize];
//        System.out.println("check! head="+head+" tail="+tail);
        if(this.head>this.tail){
            System.arraycopy(this.items,this.head,newItems,0,this.nowSize-this.head);
            System.arraycopy(this.items,0,newItems,this.nowSize-this.head,this.tail+1);
        } else {
            System.arraycopy(this.items,this.head,newItems,0,this.size);
        }
        this.head=0;
        this.tail=this.size-1;
        this.nowSize=newSize;
        this.items=newItems;
    }

    private int getPrev(int index){
        return (index-1+nowSize)%nowSize;
    }
    private int getNext(int index){
        return (index+1)%nowSize;
    }
    public void addFirst(T item){
        if(size==nowSize-1) {
            this.resize(nowSize*2);
        }
        if(size==0) {
            items[head]=items[tail]=item;
        } else {
            this.head=getPrev(this.head);
            items[head]=item;
        }
        size++;
    }

    public void addLast(T item){
        if(size==nowSize-1) {
            this.resize(nowSize*2);
        }
        if(size==0) {
            items[head]=items[tail]=item;
        } else {
            this.tail=getNext(this.tail);
            items[tail]=item;
        }
        size++;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public int size(){
        return this.size;
    }

    public void printDeque(){
        for(int i=head;i!=tail;i=getNext(i)){
            System.out.print(items[i]+" ");
        }
        System.out.println(items[tail]);
    }
    public T removeFirst(){
        if(size==0){
            return null;
        }
        T res=items[head];
//        System.out.println("check:"+res+" "+head+" "+tail+" "+nowSize);
        head=getNext(head);
        size--;
        if(size==0){
            tail=head;
        }
        if(size*3.5<=nowSize && nowSize>=32){
            resize(nowSize/2);
        }
        return res;
    }

    public T removeLast(){
//        System.out.println(nowSize+" "+size+" "+head+" "+tail);
//        for(T item:items){
//            System.out.print(item+" ");
//        }
//        System.out.println("");
        if(size==0){
            return null;
        }
        T res=items[tail];
        tail=getPrev(tail);
        size--;
        if(size==0){
            head=tail;
        }
        if(size*3.5<=nowSize && nowSize>=32) {
            resize(nowSize/2);
        }
        return res;
    }

    public T get(int index){
        if(index>=size)return null;
        int now=head;
        for(int i=1;i<=index;i++){
            now=getNext(now);
        }
        return items[now];
    }

    private T doGetRecursive(int now,int index){
        if(index==0){
            return items[now];
        }
        return doGetRecursive(getNext(now),index-1);
    }
    public T getRecursive(int index){
        if(index>=size)return null;
        return doGetRecursive(head,index);
    }

}
