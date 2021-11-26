import java.util.ArrayList;

// ***************************_DATE:-18/07/2021_***************************
public class heap {
    // defines Variables
    private ArrayList<Integer> arr;
    private int size = 0;
    private boolean isMax = true; // by default max heap

    // initialize our variables
    private void initialize(boolean isMax) {
        this.arr = new ArrayList<>();
        this.size = 0;
        this.isMax = isMax;
    }

    // ==========================================================================================================
    // ===================================
    // ===========_CONSTRUCTOR_===========
    public heap() { // default constructor
        initialize(true);
    }

    public heap(int[] arr, boolean isMax) { // O(n) --> with proof
        initialize(isMax);
        for (int ele : arr)
            this.arr.add(ele);

        for (int i = this.arr.size() - 1; i >= 0; i--)
            downHeapify(i);

        this.size = arr.length;
    }

    // ==========================================================================================================
    // ======================================
    // ===========_basic function_===========
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // ==========================================================================================================
    // ===========================
    // ===========_ADD_===========
    public void add(int data) { // O(logn)
        this.arr.add(data);
        this.size++;
        upheapify(this.size - 1);
    }

    // ==============================
    // ===========_REMOVE_===========
    public int remove() throws Exception { // O(logn)
        if (this.arr.size() == 0)   // Exception Handling
            throw new Exception("NULL_POINTER");

        int rv = this.arr.get(0);
        int n = this.arr.size();

        swap(0, n - 1);
        this.arr.remove(n - 1);
        this.size--;

        downHeapify(0);
        return rv;
    }

    // ============================
    // ===========_PEEK_===========
    public int peek() throws Exception{ // O(1)
        if (this.arr.size() == 0)   // Exception Handling
            throw new Exception("NULL_POINTER");
        return this.arr.get(0);
    }

    private void swap(int i, int j) { // O(1)
        int ei = this.arr.get(i);
        int ej = this.arr.get(j);

        this.arr.set(i, ej);
        this.arr.set(j, ei);

    }

    // ==========================================================================================================
    // =============================================
    // ===========_Implements Comparable_===========
    private int compareTo(int a, int b) {
        // give preferance to 'a -> this'
        if (this.isMax)
            return this.arr.get(a) - this.arr.get(b);

        else
            return this.arr.get(b) - this.arr.get(a);
    }

    // ==========================================================================================================
    // =================================
    // ===========_UpHeapiFy_===========
    private void upheapify(int ci) { // O(logn)
        int pi = (ci - 1) / 2; // parent index
        if(pi >= 0 && compareTo(ci, pi) > 0) {
            swap(ci, pi);
            upheapify(ci);
        }
    }

    // ===================================
    // ===========_DownHeapiFy_===========
    private void downHeapify(int pi) { // O(logn)
        int maxIdx = pi;
        int lci = 2 * pi + 1; // left child
        int rci = 2 * pi + 2; // right child

        if (lci < this.arr.size() && compareTo(lci, maxIdx) > 0)
            maxIdx = lci;
        if (rci < this.arr.size() && compareTo(rci, maxIdx) > 0)
            maxIdx = rci;

        if (maxIdx != pi) {
            swap(maxIdx, pi);
            downHeapify(maxIdx);
        }
    }
}