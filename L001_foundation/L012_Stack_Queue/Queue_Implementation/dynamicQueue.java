public class dynamicQueue extends queue {
    public dynamicQueue() {
        super();
    }

    public dynamicQueue(int size) {
        super(size);
    }

    public dynamicQueue(int[] arr) {
        super.initializeVariables(2 * arr.length);

        for (int i = 0; i < arr.length; i++)
            super.push_(arr[i]);
    }

    @Override
    public void push(int data) {
        if (super.capacity == super.elementCount) {
            int f = super.front;
            int n = super.capacity;

            int[] temp = new int[n];
           
            for (int i = 0; i < n; i++) {
                int idx = (f + i) % n;
                temp[i] = super.arr[idx];
            }

            super.initializeVariables(2 * super.capacity);
            for (int elem : temp)
                super.push_(elem);
        }

        super.push_(data);
    }
}