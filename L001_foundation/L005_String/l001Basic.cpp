#include <iostream> 
#include<vector>
using namespace std;


void test1() {
    string str = "abcdefh";
    str += 'g'; // o(1)   // abcdefhg                  // += , push_back both arr perform same operation 
    str.push_back('o'); // o(1) //abcdefhgo            // in o(1) time complexity 

    string str1 = str; // o(n)->create copy of str which pointing on new address ;
    str1 += 'p';  // abcdefhgop

    str = str + 'a';      // o(n) //abcdefhgoa               
    char ch = str[3];
    cout << str << " " << str1 << endl; //o(n)
}

void test2() {
    string str = "";
    for(int i = 0; i < (int)1e6; i++) {      // o(n)
        str += to_string(i);   // o(1)
    }

    cout << str << endl;
}

string withoutX2(string str) {
    string ans = "";

    for(int i = 0; i < str.length(); i++) {
        if(i < 2 && str[i] != 'x') {
            sb += str[i];
        }else if(i >= 2) {
            sb += str[i];

        }
    }

    return ans;
} 
// Question ======================================================
    // compression

string compression1_mathod1(string str) {
    int i = 0, j = 0, count = 0;
    string ans = "";

    while(j < str.length()) {
        if(count == 0) {
            ans += str[i];
        }
        
        char c1 = str[i];
        char c2 = str[j];

        if(c1 == c2) {
            j++;
            count++;
        } else {
            ans += to_string(count);
            i = j;
            count = 0;
        }
    }

    ans += to_string(count);

    return ans;
}

string compression1_mathod2(string str) {
    if(str.length() == 0) {
        return "";
    }

    string ans = "";

    char prevChar = str[0];
    int i = 1;
    while(i <= str.length()) {
        int count = 1;
        while( i < str.length() && prevChar == str[i] ) {
            count++;
            prevChar = str[i];
            i++;
        }

        ans += (prevChar);
        ans += to_string(count);

        if(i == str.length() ) break;

        prevChar = str[i];
        i++;
    }

    return ans;
}

string compression2(string str) {

    vector<int> freq(26,0);
    for(int i = 0; i < str.length(); i++) {
        int idx = str[i] - 'a';
        freq[idx]++;
    }

    string ans = "";
    for(int i = 0 ; i < freq.size(); i++) {
        if(freq[i] > 0) {
            char ch = (char)('a' + i);
            ans += (ch);
            ans += to_string(freq[i]);
        }
    }

    return ans;
}

string toggle(string str) {
    string ans = "";
    for(int i = 0 ; i < str.length(); i++) {
        char ch = str[i];

        if(ch >= 'a' && ch <= 'z') {
            char uc = (char)('A' - 'a' + ch);
            ans += uc;
        }else if(ch>='A' && ch<='Z') {
            char lc = (char)('a' - 'A' + ch);
            ans += lc;
        }
    }

    return ans;
}

// palindromic substring
bool isPalindrome(string str) {
    int i = 0, j = str.length() - 1;
    while(i < j) {
        if(str[i++] != str[j]) return false;
    }

    return true;
}
void palindromicSubstring(string str) {
    for(int i = 0; i < str.length(); i++) {
        for(int j =i ; j < str.length(); j++) {
            string ss =  str.substr(i,j-i+1);
            if(isPalindrome(ss)) cout << (ss) << endl;
        }
    }
}

// String With Difference Of Every Two Consecutive Characters
string string_With_Diff_Of_Every_Two_Consecutive_Number(string str) {
    if(str.length() <= 1) return str;

    string ans = "";
    for(int i = 0; i < str.length() - 1; i++) {
        char ch = str[i];
        char nextCh = str[i+1];
        int diff = (int)(nextCh - ch);

        ans += ch;
        ans += to_string(diff);

    }
    ans += (str[str.length()-1]);
    return ans;
}

//Permutation => method 2
//Permutation
void appendCharInString(string str, char ch, vector<string> &ans)
{
    for (int i = 0; i <= str.length(); i++)
    {
        string s = str.substr(0, i) + ch + str.substr(i);
        ans.push_back(s);
    }
}

vector<string> permutation(string str)
{
    vector<string> ans;
    ans.push_back("");

    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];

        vector<string> smallAns;
        for (string s : ans)
            appendCharInString(s, ch, smallAns);

        ans = smallAns;
    }

    return ans;
}

// leetcode -> 541 => reverse string II
void reverseRange(string& s, int i, int j) {
    while(i <  j) {
        swap(s[i++] , s[j--]);
    }
}
string reverseStr(string s, int k) {
    if(k == 0 || k == 1 || s.length() <= 1) return s;
    
    int i = 0 , n = s.length();
    
    while(i < n) {
        if(i + k - 1 < n) {
            reverseRange(s, i , i + k - 1);
            i += 2 * k;
        } else {
            reverseRange(s , i , n - 1);
            break;
        }
    }
    
    return s;
}


int main() {
   string str;
   cin >> str;

   string s = string_With_Diff_Of_Every_Two_Consecutive_Number(str);
   cout << s << endl;
   return 0;
} 