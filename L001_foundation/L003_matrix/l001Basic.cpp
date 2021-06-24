#include<iostream>
#include<vector>
using namespace std;

void test1() {
    int n,m;
    cin >> n >> m;

    vector<vector<int>> arr(n,vector<int>(m,0));

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m ; j++) {
            cin >> arr[i][j];
        }
    }

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m ; j++) {
            cout << arr[i][j] << " ";
        }

        cout << endl;
    }
}

// question =============================================================================

void printOutput() {
    int n,m;
    cin >> n >> m;
    vector<vector<int>> arr(n, vector<int>(m,0));

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m ; j++) {
            cin >> arr[i][j];
        }
    }

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m ; j++) {
            cout<<(arr[i][j]) << " ";
        }
        cout<<endl;
    }
}

void waveTraversal(vector<vector<int>> arr) {
    int row = arr.size();
    int col = arr[0].size();

    for(int j = 0 ; j < col; j++) {
        if(j % 2 == 0) {
            for(int i = 0; i < row; i++) {
                cout<<(arr[i][j])<<endl;
            }
        }else {
            for(int i = row - 1 ; i >= 0 ; i--) {
                cout<<(arr[i][j])<<endl;
            }
        }
    }
}

void waveTraversal2(vector<vector<int>> arr) {
    int row = arr.size();
    int col = arr[0].size();

    for(int i = 0 ; i < row; i++) {
        if(i % 2 == 0) {
            for(int j = 0; j < col; j++) {
                cout<<(arr[i][j])<<endl;
            }
        }else {
            for(int j = col - 1 ; j >= 0 ; j--) {
                cout<<(arr[i][j])<<endl;
            }
        }
    }
}

void exiPointsOfMatrix(vector<vector<int>> arr) {
    int dir = 0; //west
    int i = 0;
    int j = 0;

    while(i < arr.size() && i >= 0 && j < arr[0].size() && j >= 0) {
        dir = (dir + arr[i][j]) % 4; //direction
        if(dir == 0) {
            j++;
        }else if(dir == 1) {
            i++;
        }else if(dir == 2) {
            j--;
        } else if(dir == 3) {
            i--;
        }
        
    }
    
    // check for valid index
    if(i < 0) {
        i++;
    } else if(i >= arr.size() ) {
        i--;
    } else if( j < 0) {
        j++;
    } else {
        j--;
    }

    cout << i << endl << j << endl;
}

// diagonal traversal
void printAllDiagonals(vector<vector<int>> arr) {
    for(int gap = 0; gap < arr[0].size(); gap++ ) {
        for(int i = 0, j = gap ; i < arr.size() && j < arr[0].size(); i++, j++) {
            cout<<(arr[i][j])<<endl;
        }
    }
}

// saddle point 
void saddlePoint(vector<vector<int>> arr) {
    for(int r = 0 ; r < arr.size(); r++) {
        int minElem = (int)1e8;
        int c =0 ;
        
        // find minimum elem in a row
        for(int j = 0; j < arr[0].size(); j++) {
            if(minElem > arr[r][j]) {
                minElem = arr[r][j];
                c = j;
            }
        }
            
        bool isSaddle = true;
        for(int i = 0; i < arr.size(); i++) {
            if(arr[i][c] > minElem) {
                isSaddle = false;
                break;
            }
        }
        
        if(isSaddle == true) {
            cout<<(minElem)<<endl;
            return;
        }
    }

    cout<<("Invalid input")<<endl; 
    
}

// Search In A Sorted 2D Array
void searchInASorted2dArray(vector<vector<int>> arr, int d) {
    int i = 0;
    int j = arr[0].size()-1;

    while(true) {
        if(arr[i][j] < d) {
            i++;
            if(i == arr.size()) {
                cout << ("Not Found") << endl;
                break;
            }
        } else if(arr[i][j] > d) {
            j--; 
            if(j < 0 ) {
                cout << ("Not Found") << endl;
                break;
            }
        } else if(arr[i][j] == d) {
            cout<< i << endl << j;
            return;
        }
    }
}

// rotate 90 degree
void transpose(vector<vector<int>> arr) {
    for(int i = 0; i < arr.size(); i++) {
        for(int j = 0; j <= i; j++) {
            int temp = arr[i][j];
            arr[i][j] = arr[j][i];
            arr[j][i] = temp;
        }
    }
}
 void reverseCol(vector<vector<int>> arr) {
    int c1 = 0;
    int c2 = arr[0].size()-1;

    while(c1 < c2) {
        for(int r = 0; r < arr.size(); r++) {
            int elem1 = arr[r][c1];
            int elem2 = arr[r][c2];
            
            // swap each elem
            arr[r][c1] = elem2;
            arr[r][c2] = elem1;
        }
            
        // update
        c1++;
        c2--;
    }
}
void display(vector<vector<int>> arr) {
    for(int i = 0; i < arr.size(); i++ ) {
        for(int j = 0; j < arr[0].length ; j++) {
            System.out.print(arr[i][j] + " ");
        }

        System.out.println();
    }
}

void rotateBy90Degrees(vector<vector<int>> arr) {
    // 1. transpose
    transpose(arr);

    // 2. reverse column
    reverseCol(arr);

    // print updated arr
    display(arr);

}

int main() {
    // test1();
    int n;
    int m;
    cin >> n >> m;
    vector<vector<int>> arr(n,vector<int>(m,0));
    for(int i = 0; i < arr.size(); i++) {
        for(int j = 0; j < arr[0].size(); j++) {
            cin >> arr[i][j];
        }
    }

    printAllDiagonals(arr);
    return 0;
}