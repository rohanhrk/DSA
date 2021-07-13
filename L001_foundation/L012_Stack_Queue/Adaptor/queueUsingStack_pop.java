import java.util.Stack;

public class queueUsingStack_pop {
    Stack<Integer> st = new Stack<>();
    Stack<Integer> temp = new Stack<>();

    public int size() {
        return this.st.size();
    }

    public boolean isEmpty() {
        return this.st.isEmpty();
    }

    private void transferToAnotherStack() {
        while (this.st.size() != 0)
            this.temp.push(this.st.pop());
    }

    public void add(int data) {
        // 1. transfer all the element of st stack to temp stack
        transferToAnotherStack();
        
        // 2. push data in st
        this.st.push(data);

        // 3. push all the elem of temp stack to st
        while (this.temp.size() != 0)
            this.st.push(this.temp.pop());
    }

    public int top() {
        return this.st.peek();
    }

    public int remove() {
        return this.st.pop();
    }
}