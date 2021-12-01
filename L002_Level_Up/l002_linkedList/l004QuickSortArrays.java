import java.util.Random;
public class l004QuickSortArrays {
	public static void swap(int[] arr, int i, int j) {
		// System.out.println("3");
		int a = arr[i], b = arr[j];
		arr[i] = b;
		arr[j] = a;
	}

	public static int segregate(int[] arr, int pivotIdx, int sp, int ep) {
		// System.out.println("2");
		swap(arr, pivotIdx, ep);

		int ptr = sp - 1, itr = sp;
		while (itr <= ep) {
			if (arr[itr] <= arr[ep]) {
				swap(arr, ++ptr, itr);
			}
			itr++;
		}

		return ptr;
	}

	public static void quickSort(int[] arr, int si, int ei) {
		if (si > ei)
			return;
        
		Random rand = new Random();
		int pivotIdx = rand.nextInt(ei - si + 1) + si;
		pivotIdx = segregate(arr, pivotIdx, si, ei);
        
        boolean flag = true;
        for(int i = si + 1; i <= ei; i++) {
            if(arr[i - 1] > arr[i]) {
                flag = false;
                break;
            }
        }
        if(flag) return;
		quickSort(arr, si, pivotIdx - 1);
		quickSort(arr, pivotIdx + 1, ei);
	}

	public static void main(String[] args) {
		int[] arr = { -12, 2, 7, 4, 34, 0, -1, -50, 7, 23, 4, 3 };
		quickSort(arr, 5, 8);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
