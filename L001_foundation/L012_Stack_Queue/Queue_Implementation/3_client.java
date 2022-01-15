import javax.management.Query;

public class client {
    public static void main(String[] args) throws Exception {
        // queue que = new queue(10);
        // for (int i = 1; i <= 10; i++)
        // que.push(i * 100);

        // System.out.println(que.pop());
        // System.out.println(que.pop());
        // System.out.println(que.pop());
        // System.out.println(que.pop());
        // System.out.println(que.pop());

        // System.out.println(que);

        dynamicQueue dq = new dynamicQueue(5);
        for (int i = 0; i < 15; i++)
            dq.push(i * 1);
        System.out.println(dq.pop());
        System.out.println(dq.pop());
        System.out.println(dq.pop());
        System.out.println(dq.pop());
        System.out.println(dq.pop());
        System.out.println(dq);
    }
}