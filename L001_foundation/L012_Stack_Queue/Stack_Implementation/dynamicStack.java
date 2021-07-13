public class dynamicStack extends stack {

    // constructor
    public dynamicStack() {
        // this.capacity = 10;
        // initializevariablie();

        super(); // super -> parent class (stack,java) ko access karta he
    }

    public dynamicStack(int size) {
        // this.capacity = size;
        // initializevariablie();

        super(size);  //
    }

    public dynamicStack(int[] arr) {
        super.initializeVariables(arr.length);

        for (int ele : arr)
            super.push_(ele);
    }

    // push
    protected void push_(int data) {
        if (super.capacity == super.elementCount) {
            int[] temp = super.arr;  // copy old data
            super.initializeVariables(2 * super.capacity); // new space allocate for new upcoming data.
            for (int ele : temp) // copy old data in new space.
                super.push_(ele);
        }
    }

    public void push(int data) {
        push_(data);
    }
}