#include<iostream>
#include<vector>

using namespace std;

void test1() {
    int* arr = new int [10];
    for(int i=0; i<20; i++) {
        cout << arr[i] <<" ";
    }
}

void test2() {
    int n;
    cin >> n;
    vector<int> arr(n, 20);
    for(int i = 0; i < arr.size(); i++) {
        cout << arr[i] <<" ";

    }
}

void test3() {
    int n;
    cin >> n;
    vector<int> arr(n);
    for(int i = 0; i < arr.size(); i++) {
        cin >> arr[i];
    }

    for(int i = 0; i < arr.size(); i++) {
        cout << arr[i] <<" ";

    }
}

// ========================================= Basic Question =========================================
int maximumElem(vector<int>& arr) {
    int maxElem = -1e8;
    for(int i = 0; i < arr.size(); i++) {
        if(arr[i] > maxElem) {
            maxElem = arr[i];
        }
    }

    return maxElem;
}

int miniumElem(vector<int>& arr) {
    int minElem = 1e8;
    for(int i = 0; i < arr.size() ; i++) {
        if(arr[i] < minElem) {
            minElem = arr[i];
        }
    }

    return minElem;
}

int findElem(vector<int>& arr, int data) {
    int idx = -1;
    for(int i = 0; i < arr.size(); i++) {
        if(arr[i] == data) {
            idx = i;
            break;
        }
    }
    return idx;
}

int firstIndex(vector<int>& arr, int data) {
    int idx = -1;
    for(int i = 0; i < arr.size(); i++) {
        if(arr[i] == data) {
            idx = i;
            break;
        }
    }
    return idx;
}

int lastIndex(vector<int>& arr, int data) {
    int idx = -1;
    for(int i = arr.size()-1; i >= 0; i--) {
        if(arr[i] == data) {
            idx = i;
            break;
        }
    }
    return idx;
}

int spanOfArray(vector<int>& arr) {
    int maxElem = maximumElem(arr);
    int minElem = miniumElem(arr);

    return maxElem - minElem;
}

void swap(vector<int>& arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

void reverseArray(vector<int>& arr) {
    int i = 0;
    int j = arr.size() - 1;

    while (i < j) {
        swap(arr,i,j);
        i++;
        j--;
    }
}

int digitFrequency(int num, int data) {
    int digitCount = 0;

    while(num != 0) {
        int reminder = num % 10;
        num = num / 10;

        if(reminder == data) {
            digitCount++;
        }
    }

    return digitCount;
}

// sum of two arrays
void sumOfTwoArrays(vector<int>& arr1, vector<int>& arr2) {
    int p = arr1.size();
    int q = arr2.size();
    int r = max(p,q)+1;

    vector<int> ans(r,0);
    int i = p - 1, j = q - 1, k = r - 1, carry = 0;

    while(k >= 0) {
        int sum = carry;
        if(i >= 0) sum += arr1[i--];
        if(j >= 0) sum += arr2[j--];

        ans[k--] = sum % 10;
        carry = sum /10;
    } 

    for(int l = 0; l < ans.size(); l++) {
        if(l == 0 && ans[l] == 0) continue;

        cout << ans[l] << endl;
    }
}
// difference of two array
void diffOfTwoArrays(vector<int>& arr1,vector<int>& arr2) {
    int p = arr1.size();
    int q = arr2.size();
    int r = max(p, q);

    vector<int> ans(r,0);

    int i = p - 1, j = q - 1, k = r - 1, borrow = 0;

    while(k >= 0) {
        int num = borrow;

        if(i >= 0) num += arr1[i--];
        if(j >= 0) num -= arr2[j--];

        if(num < 0) {
            num += 10;
            borrow = -1;
        } else {
            borrow = 0;
        }

        ans[k--] = num;
    }

    bool flag = false;
    for(int l = 0; l < ans.size(); l++) {
        if(!flag && ans[l] == 0) continue;
        
        cout<<(ans[l])<<endl;
        flag = true;
    }
}
// rotate an array by k
void swapFunc(vector<int>& arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

void reverseFunc(vector<int>& arr, int i, int j) {
    while (i < j) {
        swapFunc(arr,i,j);
        i++;
        j--;
    }
}
void rotate(vector<int>& arr, int k) {
    int n = arr.size();
    reverseFunc(arr,0,n-1);
    reverseFunc(arr,0,k-1);
    reverseFunc(arr,k,n-1);
}
int main() {
   int n,k;
   cin>>n;
   vector<int> arr(n);
   for(int i = 0; i < n; i++) {
       cin >> arr[i];
   }
    
   cin >> k;
   rotate(arr,k);

   for(int elem : arr) {
       cout << elem << " ";
   }
}