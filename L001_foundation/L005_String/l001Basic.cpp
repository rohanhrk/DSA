#include <iostream> 
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
int main() {
    // test1();
    test2()
} 