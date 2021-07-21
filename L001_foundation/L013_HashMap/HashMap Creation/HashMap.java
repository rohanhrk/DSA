import java.util.ArrayList;
import java.util.LinkedList;

// =====================================================_DATE:-21/07/2021_=====================================================
public class HashMap<K, V> {
    // variables
    private LinkedList<Node>[] buckets;
    private int NoOFElements = 0; // total Number of element present in group of bucket
    private int maxSizeOfBucket = 0; // size of array-> bucket

    // Node class
    private class Node {
        K key = null;
        V value = null;

        // CONSTRUCTOR
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // overriding the toString() method
        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private void intialize(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }

        this.maxSizeOfBucket = size;
        this.NoOFElements = 0;
    }

    // 9. ********_Display()_********
    // overriding the toString() method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int sizeOfMap = this.NoOFElements;
        for (int i = 0; i < this.maxSizeOfBucket; i++) {
            LinkedList<Node> group = this.buckets[i];
            int size = group.size();
            while (size-- > 0) {
                sb.append(group.getFirst());
                if (sizeOfMap > 1)
                    sb.append(",");

                group.addLast(group.removeFirst());
                sizeOfMap--;
            }
        }

        sb.append("]");

        return sb.toString();
    }

    // HashMap ka constructor
    public HashMap() {
        intialize(10);
    }

    // *****************************************_BASIC_FUNCTION_*****************************************

    // ********_1. SIZE()_********
    public Integer size() {
        return this.NoOFElements;
    }

    // 2. ********_IsEmpty()_********
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // 1. _********groupNo()_********
    private Integer groupNo(K key) {
        Integer hc = Math.abs(key.hashCode());
        return hc % maxSizeOfBucket;
    }

    // 2. ********_group()_********
    private LinkedList<Node> group(K key) {
        int groupNo = groupNo(key);
        return this.buckets[groupNo];
    }

    // 3. ********_ContainsKey()_********
    public boolean containsKey(K key) {
        LinkedList<Node> group = group(key);
        int size = group.size();
        while (size-- > 0) {
            if (group.getFirst().key == key)
                return true;
            group.addLast(group.removeFirst());
        }
        return false;
    }

    // 4. ********_get()_********
    // if you found return value if not return null.
    public V get(K key) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if (res)
            return group.getFirst().value;

        return null;
    }

    // 5. ********_remove()_********
    public V remove(K key) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if (res) {
            this.NoOFElements--;
            return group.removeFirst().value;
        }
        return null;
    }

    // 6. ********_getOrDefault()_getOrDefault
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        if (value == null)
            return defaultValue;
        return value;
    }

    // 7. ********_keySet()_********
    private void allkeysOfGroup(LinkedList<Node> group, ArrayList<K> ans) {
        int size = group.size();
        while (size-- > 0) {
            ans.add(group.getFirst().key);
            group.addLast(group.removeFirst());
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> ans = new ArrayList<>();
        for (int i = 0; i < this.maxSizeOfBucket; i++) {
            allkeysOfGroup(this.buckets[i], ans);
        }

        return ans;
    }

    // 8. ********_put() -> set_********
    private void rehash() {
        LinkedList<Node>[] temp = this.buckets;
        intialize(2 * this.maxSizeOfBucket);
        for (int i = 0; i < temp.length; i++) {
            LinkedList<Node> group = temp[i];
            int size = group.size();
            while (size-- > 0) {
                Node node = group.removeFirst();
                put(node.key, node.value);
            }
        }
    }

    public void put(K key, V value) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if (res) {
            group.getFirst().value = value;
        } else {
            Node node = new Node(key, value);
            group.addLast(node);
            this.NoOFElements++;

            double lambda = (0.4 * this.maxSizeOfBucket);
            if (group.size() >= lambda)
                rehash();
        }
    }

    // 10. ********_putIfAbsent()_********
    public void putIfAbsent(K key, V value) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if (!res)
            put(key, value);
    }

}