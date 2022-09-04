package deque;

public class ArrayDeque<Type> {

    private Type []p;
    int size;
    int firstIndex;
    double usage_ratio;
    public ArrayDeque() {
        p=(Type[])new Object[8];
        size=0;
        firstIndex=2;
        usage_ratio=1;
    }

    public void addFirst(Type x){
        if(firstIndex==0)
            this.resize();
        p[firstIndex-1]=x;
        firstIndex--;
        size++;
    }
    /** Inserts X into the back of the list. */
    public void addLast(Type x) {
        if(size==p.length-firstIndex) {
            this.resize();
        }
        p[size+firstIndex]=x;
        size++;
    }

    /** Returns the item from the back of the list. */
    public Type getLast() {
        return p[size-1+firstIndex];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Type get(int i) {
        return p[i+firstIndex];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public Type getFirst(){
        if(size()==0)
            return null;
        return p[firstIndex];
    }

    public Type removeFirst(){
        if(size()==0)
            return null;
        usage_ratio=(double)size/p.length;
        if(usage_ratio<=0.25&&size()>4)
            resizeToHalf();
        Type temp=this.getFirst();
        p[firstIndex]=null;//if the stored stuff is big,then we set its reference to null,so we can save memory
        firstIndex++;
        size--;
        return temp;
    }
    /** Deletes item from back of the list and
     * returns deleted item. */
    public Type removeLast() {
        if(size()==0)
            return null;
        usage_ratio=(double)size/p.length;
        if(usage_ratio<=0.25&&size()>4)
            resizeToHalf();
        Type temp=this.getLast();
        p[firstIndex+size-1]=null;//if the stored stuff is big,then we set its reference to null,so we can save memory
        size--;
        return temp;
    }

    public double getUsage_ratio(){
        usage_ratio=(double)size()/p.length;
        return usage_ratio;
    }
    private void resize(){
        Type[] a=(Type[])new Object[p.length*2];//resize by multiplying the number of boxes by 2.which can be quite fast when add millions numbers;
        System.arraycopy(p,firstIndex,a,a.length/4,size);
        firstIndex=a.length/4;
        p=a;
    }

    /**In order to save memory,when memory usage ratio is less than 0.25,change array's length to it's half.*/
    private void resizeToHalf(){
        Type[] a=(Type[])new Object[p.length/2];
        System.arraycopy(p,firstIndex,a,a.length/4,size);
        firstIndex=a.length/4;
        p=a;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public void printDeque(){
        for(int i=firstIndex;i< this.size()+firstIndex;i++){
            System.out.print(this.get(i)+"  ");
        }
    }
}
