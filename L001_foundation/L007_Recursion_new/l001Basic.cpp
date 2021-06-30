#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

string solution(string str, int idx, string res) {
    if(idx == str.length()) {
        return ""; 
    }
    char ch = str[idx];
    string ans = solution(str, idx+1, res);
    if(ch == ans[0]) {
        res += ch + "*" + ans;
    }else {
        res += ch + ans;
    }
    
    return res;
}
int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */  
    string str; 
    cin >> str;
    cout << (solution(str, 0, "")) << endl;
    return 0;
}
