public class heapSort {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // li - > last index
    public static int compareTo(int[] arr, int a, int b, boolean isMax) {
        if (isMax)
            return arr[a] - arr[b];
        else
            return arr[b] - arr[a];
    }

    public static void heapify(int pi, int[] arr, int li, boolean isMax) {
        int maxIdx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (lci <= li && compareTo(arr, lci, maxIdx, isMax) > 0)
            maxIdx = lci;
        if (rci <= li && compareTo(arr, rci, maxIdx, isMax) > 0)
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(arr, maxIdx, pi);
            heapify(maxIdx, arr, li, isMax);
        }

    }

    public static void display(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--)
            heapify(i, arr, n - 1, false);

        int li = arr.length - 1;
        while (li >= 0) {
            swap(arr, 0, li--);
            heapify(0, arr, li, false);
        }

        display(arr);
    }
}