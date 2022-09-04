package deque;

public class LinkedListDeque<Type> {
    private class node{
        public node prev;
        public Type item;
        public node next;

        public node(node p,Type i,node n){
            prev=p;
            item=i;
            next=n;
        }
    }

    private node sentinel;
    private int size;

    public LinkedListDeque(){
        size=0;
        sentinel=new node(null,null,null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
    }

    public LinkedListDeque(Type []i){
        /**calling another constructor in one constructor using this(),from stackoverflow;*/
        this();
        for(Type x:i){
            this.addLast(x);
        }
    }


    public void addFirst(Type i){
        node first=new node(sentinel,i,sentinel.next);
        sentinel.next.prev=first;
        sentinel.next=first;
        size++;
    }


    public void addLast(Type i){
        node last=new node(sentinel.prev,i,sentinel);
        sentinel.prev.next=last;
        sentinel.prev=last;
        size++;
    }

    public boolean isEmpty(){
        return(this.size==0);
    }

    public int size(){
        return this.size;
    }

    public Type removeFirst(){
        if(this.size()==0)
            return null;
        node first=sentinel.next;
        first.prev=null;
        first.next.prev=sentinel;
        sentinel.next=first.next;
        first.next=null;
        this.size--;
        return first.item;
    }

    public Type removeLast(){
        if(this.size()==0)
            return null;
        node last=sentinel.prev;
        last.prev.next=sentinel;
        sentinel.prev=last.prev;
        last.prev=null;
        last.next=null;
        this.size--;
       return last.item;
    }

    public Type get(int index) {
        if (index >= this.size() || index < 0)
            System.out.println("error");
        int mid = (1 + this.size()) / 2;
        node p = sentinel.next;
        if (index <= mid)
            for (int i = 0; index != i; i++, p = p.next) ;
        if (index > mid) {
            p=sentinel.prev;
            for (int i = this.size() - 1; index != i; i--, p = p.prev) ;
        }
        return p.item;
    }

    public Type getFirst(){
        return sentinel.next.item;
    }

    public Type getLast(){
        return sentinel.prev.item;
    }
    public void printDeque(){
        node p=sentinel.next;
        for(;p!=sentinel;p=p.next)
            System.out.print(p.item+" ");
        System.out.println();
    }

    public Type getRecursive(int index){
        return getRecursiveHelper(index,0,this.sentinel.next);
    }

    private Type getRecursiveHelper(int index,int current,node p){
        if(current==index)
            return p.item;
        return getRecursiveHelper(index,current+1,p.next);
    }

}




