#include<iostream>
#include<vector>

using namespace std;

// "*" -> ye kisi chij ka adress store karta he
int *input1d(int n)
{
    // heap me arrAY CREATION using new keyword
    int *arr = new int[n];// 1. 'new'-> array abb heap me create ho jayega 
                          // 2. '*' -> ye heap pe banne wala integer array ka address store karake de raha he
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
    
    // 'input1d' function 'stack' pe bana he, so baadme wo destroy bhi ho jayega, but humne jo heap me array create kara he wo hamesa rahenge.
    // so hum logo ko us array ka adrress return karna hoga, future me utilize karne ke liye.
    return arr;   // ye humne array ka address return kara he 
}

// "*arr" -> ye use karne ka matlab hum array ka base address le rahe he (means pure array ko copy karke rakh lega)
// agar hum simple "*arr" use nhi karke "arr" ye use kara, toh woh as a variable maan lega usko.
// agar hum stack me array bana rahe he toh usme hum size h=function use nhi kar sakte, usko hume size pass karna hoga (jese niche humne "int n" pass kara he)
void output1d(int *arr, int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }
}

void test2(int *arr, int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << endl;
    }
}

void inputForStack(int n)
{
    int arr[n] = {0};
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
}

void test1()
{
    int n;
    cin >> n;

    int *a = new int(10);
    // int arr[n] = {0};
    // test2(arr, n);

    inputForStack(n);
}


void test2()
{
    int n;
    cin >> n;
    int *arr = input1d(n);  
    output1d(arr, n);
}

// for 2d Arrays.
void test3()
{
    int n, m;
    cin >> n >> m;

    int **arr = new int *[n];
    for (int i = 0; i < n; i++)
    {
        int *ar = new int[m];
        arr[i] = ar;
    }
    
    // 3d array
    int ***arrr = new int **[3];
    for (int i = 0; i < 3; i++)
    {
        arrr[i] = new int *[5];
        for (int j = 0; j < 5; j++)
        {
            arrr[i][j] = new int[7];
            for (int k = 0; k < 7; k++)
                arrr[i][j][k] = 0;
        }
    }
}

//vector  =============================================

// agar vector heap pe banega toh hum dot(.) use nhi karke arrow(->) use karenge
// aur hum get karne ke liye '[]' ye bhi use nhi kar skte, uske liye hume arr->at(i) use karna hoga  
void inputVector(vector<int> *arr)
{
    for (int i = 0; i < arr->size(); i++)
        cin >> arr->at(i);
    for (int i = 0; i < arr->size(); i++)
        cout << arr->at(i) << " ";
    cout << endl;
}

void test4()
{   
    vector<int> *arr = new vector<int>(10, 0);// vector heap pe banega abb -> uske pass pointer(jo heap pe banne wala array ka base address store karke rakhta he) aur size(array ka size) 
                                                                        // -> jab array ko pass karenge toh us array ka copy constructor fire hoga means (pointer aur size) pass hoga dusre function me
    inputVector(arr);
    for (int i = 0; i < arr->size(); i++)
        cout << arr->at(i) << " ";
}

// & -======================================================================

int **test5()
{
    int a = 10;                  // '&' -> ye use karne par woh stack ka address store karte rakhta he 
    int *b = &a;                 // '&a'-> 'a' jis bhi address pe bana hoga, '&a' likhne se wo 'a' ka adress store kar lega
    int **c = &b;                // '**' -> '*' ka address store karta he means address ka address store 

    cout << a << " " << &a << endl;
    cout << *b << " " << &b << endl;//dereferance
    cout << **c << " " << &c << endl;//dereferance           

    int **arr = new int *[1];
    arr[0] = &a;
    return arr;
}

int main() {

}

// ============================================================================================================
// stack ka disadvantage
// 1. ye bohot costly he
// 2. baadme memory/data kho bhi sakte he -> kuchbhi stack pe banta bhi he -> aur destroy bhi hota he