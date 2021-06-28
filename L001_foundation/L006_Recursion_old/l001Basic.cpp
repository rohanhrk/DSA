#include <iostream>
#include <vector>
using namespace std;

// eular function
int eulerFunction(int n){
    if (n <= 1)
    {
        cout << "Base Case: " + to_string(n) << endl;
        return n;
    }

    int count = 0;

    cout << "pre: " + to_string(n) << endl;
    count += eulerFunction(n - 1);

    cout << "in: " + to_string(n) << endl;

    count += eulerFunction(n - 2);
    cout << "post: " + to_string(n) << endl;

    return count + 3;
}

int main() {

}