public class stack {
    // ===========================================================================================================
    /* Declare Stack Variables */ 
    protected int[] arr = null;
    protected int capacity = 0; /* maximum element that array can hold in it */ 
    protected int elementCount = 0; /* No of element present in stack */ 
    protected int tos = -1; /* top of stack */ 

    /* initialize stack veriables */ 
    public void initializeVariables(int capacity) {
        this.capacity = capacity;   
        this.arr = new int[this.capacity];
        this.elementCount = 0;
        this.tos = -1;
    }

    // ===========================================================================================================
    /* 
        CONSTRUCTOR =>
        1. Default 
        2. parameterize
    */
    // 1. default constructor
    public stack() {
        initializeVariables(10); // default capacity
    }
    // 2. parameterize constructor 
    public stack(int size) {
        initializeVariables(size);
    }

    // ===========================================================================================================
    /*
        Basic Function =>
        1. size(), 
        2. isEmpty()
    */ 

    // 1. size()
    public int size() {
        return this.elementCount;
    }

    // 2. isEmpty()
    public boolean isEmpty() {
        return this.elementCount == 0;
    }
    
    /* display */ 
    @Override
    public String toString() {
        int n = this.capacity;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.elementCount; i++) {
            sb.append(this.arr[i]);
            if (i != this.elementCount - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    // ===========================================================================================================
    /* 
        Exception Handling ===>
        1. overFlowException(), 
        2. underFlowException() 
    */ 
    private void overFlowException() throws Exception {
        if (this.capacity == this.elementCount) {
            throw new Exception("stackIsFull");
        }
    }

    private void underFlowException() throws Exception {
        if (this.elementCount == 0) {
            throw new Exception("stackIsEmpty");
        }
    }

    // ===========================================================================================================
    /* 
        Stack Function =>
        1. push 
        2. pop
        3. top
    */ 

    // 1. push operator
    protected void push_(int data) {
        this.arr[++this.tos] = data;
        this.elementCount++;
    }

    public void push(int data) throws Exception {
        overFlowException();
        push_(data);
    }

    // 2. top
    public int top() throws Exception {
        underFlowException();
        return this.arr[this.tos];
    }

    // 3. pop
    protected int pop_() {
        int rv = this.arr[this.tos];
        this.arr[this.tos--] = 0;
        this.elementCount--;
        return rv;
    }

    public int pop() throws Exception {
        underFlowException();
        return pop_();
    }
}