public class 2_dynamicStack extends 1_stack {
    /*
        facts about super() ====>
        1. "this" keyword pehle dynamics stack me search karta he, apne pass se variable ya function
        nehi milne par, "this" keyword parent class pe jake dhundta he.

        2. "super" keyword direct parent class me jakar variable aur function ko search karta he.  

        3. "super" keyword "this" keyword se fast work karta he. 
    */ 

    public dynamicStack() {
        super();
    }

    public dynamicStack(int size) {
        super(size);
    }

    public dynamicStack(int[] arr) {
        super.initializeVariables(2 * arr.length);

        for (int ele : arr)
            super.push_(ele);
    }

    @Override
    public void push(int data) throws Exception{
        if (super.capacity == super.elementCount) {
            int[] temp = super.arr; // copy old data
            super.initializeVariables(2 * super.capacity); // new space allocate for new upcoming data.
            for (int ele : temp) // copy old data in new space.
                super.push_(ele);
        }

        super.push(data);
    }

}