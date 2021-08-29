public class queue {
    protected int[] arr = null;
    protected int capacity = 0; // maximum element that array can hold in it
    protected int elementCount = 0; // No of element present in an array
    protected int front = 0; // denote where element is present
    protected int back = 0; // denote where element would be find

    // ******************_basic_function_******************

    public int size() {
        return this.elementCount;
    }

    public boolean isEmpty() {
        return this.elementCount == 0;
    }

    // toString -> wo pehle se java me likhi hui he
    // hame isko over ride karna hoga
    // The toString() method returns the string representation of the object.
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

    // // display
    // public String display() {
    //     int f = this.front;
    //     int n = this.elementCount;

    //     StringBuilder sb = new StringBuilder();
    //     sb.append("[");
    //     for (int i = 0; i < n; i++) {
    //         int idx = (f + i) % n;
    //         sb.append(this.arr[idx]);
    //         if (i != n - 1)
    //             sb.append(", ");
    //     }
    //     sb.append("]");

    //     return sb.toString();
    // }

    // ******************_Constructor_******************
    public void initializeVariables(int capacity) {
        this.capacity = capacity;
        this.arr = new int[this.capacity];
        this.elementCount = 0;
        this.front = 0;
        this.back = 0;
    }

    public queue() {
        initializeVariables(10); // default capacity
    }

    public queue(int size) {
        initializeVariables(size);
    }

    // ******************_Exception_******************
    private void overFlowException() throws Exception {
        if (this.capacity == this.elementCount) {
            throw new Exception("queueIsFull");
        }
    }

    private void underFlowException() throws Exception {
        if (this.elementCount == 0) {
            throw new Exception("queueIsEmpty");
        }
    }

    // ******************_Stack_function_******************
    // 1. push operator
    protected void push_(int data) {
        this.arr[this.back] = data;

        this.elementCount++;
        this.back = (this.back + 1) % this.capacity;
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
        this.arr[this.front] = 0;

        this.elementCount--;
        this.front = (this.front + 1) % this.capacity;
        return rv;
    }

    public int pop() throws Exception {
        underFlowException();
        return pop_();
    }
}
