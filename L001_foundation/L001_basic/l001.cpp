#include <iostream>
using namespace std;

void printHello() {
    cout<<"Hi there!"<<endl;
}

void printZ() {
    cout<<("*****")<<endl;
    cout<<("   *")<<endl;
    cout<<("  *")<<endl;
    cout<<(" *")<<endl;
    cout<<("*****")<<endl;
}

void workWithDataType() {
    int a=10;
    int b=20;

    cout<<"value of A: "+ to_string(a)<<endl;
    cout<<"value of B: "+ to_string(b)<<endl;

}
void gradingSystem(int marks) {
    if (marks > 90) {
        cout<<("excellent");
    } else if (marks > 80 && marks <= 90) {
        cout<<("good");
    } else if (marks > 70 && marks <= 80) {
        cout<<("fair");
    } else if (marks > 60 && marks <= 70) {
        cout<<("meets expectstions");
    } else {
        cout<<("below par");
    }
}

bool isPrime_(int n) {
    bool prime = true;
    for(int i = 2; i*i <= n; i++) {
        if(n%i == 0){
            prime = false;
            break;
        }
    } 
    return prime;
}

void isPrime() {
    int n;
    cin>>n;

    bool ans = isPrime_(n);
    if(ans) {
        cout<<"prime"<<endl;
    }else {
        cout<<"not prime"<<endl;
    }
} 

void printAllPrime(int a, int b){
    for(int i=a; i <= b ; i++) {
        bool ans = isPrime_(n);
        if(ans) {
            cout>>i<<endl;
        }
    }
}

void printFibbonacciNumbersTillN(int n) {
    int a = 0;
    int b = 1;
    int c = 0;

    for (int i = 0; i < n; i++) {
        c = a+b;
        cout>>(a)<<endl;

        a = b;
        b = c;
    }
}

int countDigit_(int n) {
    int count = 0;

    while (n != 0) {
      n = n / 10;
      count++;
    }

    return count;
  }
void countDigit(int n) {

    int count = countDigit_(n);
    cout<<(count)<<endl;
  }


void DigtOfANum(int n) {
        int count = countDigit_(n);
        int power = count-1;
        int divisor = (int) pow(10,power);
        while (divisor != 0) {
            int digit = n/divisor;
            cout<<(digit)<<endl;

            n = n%divisor;
            divisor = divisor/10;
        }
    }

void reverseNumber(int n) {
    int temp = n;

    while(n!=0) {
        int quotient = n/10;
        int reminder = n%10;

        cout<<reminder<<endl;

        n = quotient;
    }
}


int GCD(int a, int b) {
    int divisor = a;
    int dividend = b;
    int reminder;
    while (dividend % divisor != 0) {
        reminder =dividend % divisor;
        
        dividend = divisor;
        divisor = reminder;
    }

    return divisor;
}
int LCM(int a, int b, int gcd) {
    return (a/gcd)*b;
}

// cout << (boolalpha)<<y << endl;
bool PythagoreanTriplet(int a, int b, int c) {
    if((a*a + b*b == c*c) || (b*b + c*c == a*a) || (c*c + a*a == b*b) ) {
        return true;
    }

    return false;
}

void  BenjaminBulbs(int n) {
    for(int i=1; i*i<=n;i++) {
        cout<<i*i<<endl;
    }
}
int main() {
    // printHello();
    printZ();
    // workWithDataType();
    // int n;
    // cin>> n;
    // gradingSystem(n);
    // isPrime();
    // countDigit();
    return 0;
}