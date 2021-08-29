import java.util.Stack;

public class queueUsingStack_push {
    Stack<Integer> st = new Stack<>();
    Stack<Integer> temp = new Stack<>();
    int peekVal = 0;

    public int size() {
        return this.st.size();
    }

    public boolean isEmpty() {
        return this.st.isEmpty();
    }

    // O(1)
    public void add(int data) {
        if (this.st.size() == 0)
            this.peekVal = data;
        this.st.push(data);
    }

    // O(1)
    public int peek() {
        return this.peekVal;
    }

    // ***************_remove_***************
    private void transferToAnotherStack() {
        while (this.st.size() != 0)
            this.temp.push(this.st.pop());
    }

    // O(n)
    public int remove() {
        transferToAnotherStack();
        int rv = this.temp.pop();

        while (this.temp.size() != 0)
            this.st.add(this.temp.pop());

        return rv;
    }
}