public class 3_client {
    public static void main(String[] args) throws Exception {
        // int[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        dynamicStack st = new dynamicStack(5);
        for (int i = 1; i < 20; i++)
            st.push(i * 10);

        System.out.println(st);
    }
}
