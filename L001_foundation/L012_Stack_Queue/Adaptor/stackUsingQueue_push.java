import java.util.Queue;
import java.util.LinkedList;

public class stackUsingQueue_push {
    Queue<Integer> que = new LinkedList<>();
    Queue<Integer> temp = new LinkedList<>();

    int peekVal = 0;

    public int size() {
        return this.que.size();
    }

    public boolean isEmpty() {
        return this.que.isEmpty();
    }

    // O(1)
    public void push(int data) {
        this.peekVal = data;
        this.que.add(data);
    }

    // O(1)
    public int peek() {
        return this.peekVal;
    }

    // ***************_remove_***************
    private void transferToAnotherQueue() {
        while (this.que.size() != 1) {
            this.temp.add(this.que.remove());
        }
    }

    // O(n)
    public int pop() {
        transferToAnotherQueue();
        int rv = this.que.remove();

        while (this.temp.size() != 0)
            this.que.add(this.temp.remove());

        return rv;
    }
}