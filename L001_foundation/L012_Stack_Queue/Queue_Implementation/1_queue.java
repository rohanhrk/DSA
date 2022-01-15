public class 1_queue {

    /* Variables define */ 
    protected int[] arr = null;
    protected int capacity = 0; // maximum element that array can hold in it
    protected int elementCount = 0; // No of element present in an array
    protected int front = 0; // denote where element is present
    protected int back = 0; // denote where element would be find

    /* initialising variables */ 
    public void initializeVariables(int capacity) {
        this.capacity = capacity;
        this.arr = new int[this.capacity];
        this.elementCount = 0;
        this.front = 0;
        this.back = 0;
    }

    // ============================================================================================================
    /*
        constructor =>
        1. default constructor
        2. parameterize constructor
    */ 
    // 1. default construct
    public queue() {
        initializeVariables(10); // default capacity
    }
    // 2. param construct
    public queue(int size) {
        initializeVariables(size);
    }

    // ============================================================================================================
    /* 
        exception handling =>
        1. overFlowException() => when size is full
        2. underFlowException() => when size is empty
    */ 
    // 1. overFlowException()
    private void overFlowException() throws Exception {
        if (this.capacity == this.elementCount) {
            throw new Exception("queueIsFull");
        }
    }
    // 2. underFlowException
    private void underFlowException() throws Exception {
        if (this.elementCount == 0) {
            throw new Exception("queueIsEmpty");
        }
    }

    // ============================================================================================================
    /*
        basic function =>
        1. size()
        2. isEmpty
    */ 
    // 1. size()
    public int size() {
        return this.elementCount;
    }   
    // 2. isEmpty()
    public boolean isEmpty() {
        return this.elementCount == 0;
    }

    // ============================================================================================================
    // display Queue

    /*
        toString -> wo pehle se java me likhi hui he
        hame isko over ride karna hoga
        The toString() method returns the string representation of the object.
    */ 
    @Override
    public String toString() {
        // return "pepcoding";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.elementCount; i++) {
            int idx = (this.front + i) % this.capacity;
            sb.append(this.arr[idx]);
            if (i != this.elementCount - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }


    // ============================================================================================================
    /* 
        Queue function =>
        1. push()
        2. pop()
        3. top();
    */ 
    // 1. push operator
    protected void push_(int data) {
        this.arr[this.back++] = data;
        this.back %= this.capacity;
        this.elementCount++;
    }

    public void push(int data) throws Exception {
        overFlowException();
        push_(data);
    }

    // 2. front
    public int front() throws Exception {
        underFlowException();
        return this.arr[this.front];
    }

    // 3. pop
    protected int pop_() {
        int rv = this.arr[this.front];
        this.arr[this.front++] = 0;
        this.front %= this.capacity;
        this.elementCount--;
        return rv;
    }

    public int pop() throws Exception {
        underFlowException();
        return pop_();
    }
}
