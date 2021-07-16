import java.util.Queue;
import java.util.LinkedList;

public class stackUsingQueue_pop {
    Queue<Integer> que = new LinkedList<>();
    Queue<Integer> temp = new LinkedList<>();

    public int size() {
        return this.que.size();
    }

    public boolean isEmpty() {
        return this.que.isEmpty();
    }
    
    // ***************_push()_***************
    private void transferToAnotherQueue() {
        while (this.que.size() != 0)
            this.temp.add(this.que.remove());
    }

    // O(n)
    public void push(int data) {
        transferToAnotherQueue();

        this.que.add(data);

        while (this.temp.size() != 0)
            this.que.add(this.temp.remove());
    }
     
    // ***************_peek()_***************
    // O(1)
    public int peek() {
        return this.que.peek();
    }

    // ***************_remove_***************
    // O(1)
    public int pop() {
        return this.que.remove();
    }
}