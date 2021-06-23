#include <iostream>
using namespace std;

void printSquare(int row) {
    int nst = row; //no. of star

    for(int r =1; r<=row;r++) {
        for(int cst = 1; cst <= nst ; cst++) {
            cout<< "*";
        }

        cout<<endl;
    }
}

void printTriangle(int row) {
    int nst = 1;

    for(int r =1 ; r <= row; r++) {
        for(int cst = 1; cst <= nst; cst++) {
            cout<< "*";
        }

        nst++;
        cout<<endl;
    }
}
void printMirrorTriangle(int row) {
    int nst = 1;
    int nsp = row-1;

    for(int r =1 ; r <= row; r++) {

        // space
        for(int csp = 1; csp <= nsp; csp++) {
            cout<< "\t";
        }

        // star
        for(int cst = 1; cst <= nst; cst++) {
            cout<< "*\t";
        }

        nst++;;
        nsp--;
        cout<<endl;
    }
}


void printInvertedTriagle(int row) {
    int nsp = 0;
    int nst = row;

    for (int r = 1; r <= row; r++) {
        // star
        for (int cst = 1; cst <= nst; cst++) {
            cout<<("*\t");
        }

        // space
        for (int csp = 1; csp <= nsp; csp++) {
            cout<<("\t");
        }

        nsp++;
        nst--;
        cout<<endl;
    }
}

void printDiamond(int row) {
    int nst = 1;
    int nsp = row/2;

    for(int r =1 ; r <= row; r++) {

        // space
        for(int csp = 1; csp <= nsp; csp++) {
            cout<<("\t");
        }

        // star
        for(int cst = 1; cst <= nst; cst++) {
            cout<<("*\t");
        }
        
        if(r <= row/2) {
            nsp--;
            nst+=2;
        } else  {
            nsp++;
            nst-=2;
        }
        cout<<endl;
    }
}

void mirrorInvertedTriangle(int row) {
    int nsp = 0;
    int nst = row;

    for (int r = 1; r <= row; r++) {
        // space
        for (int csp = 1; csp <= nsp; csp++) {
            cout<<("\t");
        }

        // star
        for (int cst = 1; cst <= nst; cst++) {
            cout<<("*\t");
        }
        nsp++;
        nst--;
        cout<<endl;
    }
}

void printHollow(int row) {
    int nsp = 1;
    int nst = (row + 1) / 2;

    for (int r = 1; r <= row; r++) {

        // star
        for (int cst = 1; cst <= nst; cst++) {
            cout<<("*\t");
        }
        
        // space
        for (int csp = 1; csp <= nsp; csp++) {
            cout<<("\t");
        }

        // star
        for (int cst = 1; cst <= nst; cst++) {
            cout<<("*\t");
        }

        if (r <= row / 2) {
            nst--;
            nsp += 2;
        } else {
            nst++;
            nsp -= 2;
        }
        cout<<endl;
    }
}
    
void pattern15_mathod1(int row) {
    int nsp = row / 2;
    int nst = 1;

    int rCount = 1;
    int cCount;
    for (int r = 1; r <= row; r++) {
        // space
        for (int csp = 1; csp <= nsp; csp++) {
            cout<<("\t");
        }

        // star
        cCount = rCount;
        for (int cst = 1; cst <= nst; cst++) {
            cout<<cCount<<"\t";
            if (r > 1 && r < row) {
                if (cst <= nst / 2) {
                    cCount++;
                } else {
                    cCount--;
                }
            }

        }

        // change
        if (r <= row / 2) {
            nst += 2;
            nsp--;
            rCount++;
        } else {
            nst -= 2;
            nsp++;
            rCount--;
        }
        cout<<endl;
    }
}

void printPattern15_mathod2(int row) {
    int nsp = row / 2;
    int nst = 1;

    for (int r = 1; r <= row; r++) {
        // space
        for (int csp = 1; csp <= nsp; csp++) {
            cout<<("\t");
        }

        // star
        int count = r;
        if (r > (row / 2) + 1) {
            count = row - r + 1;
        }
        for (int cst = 1; cst <= nst; cst++) {
            cout<<count << "\t";

            if(cst <= nst/2) {
                count++;
            }else {
                count--;
            }

        }

        // change
        if (r <= row / 2) {
            nst += 2;
            nsp--;
        } else {
            nst -= 2;
            nsp++;
        }
        cout<<endl;
    }
}

void printPattern7(int row) {
    int nsp = 0;
    int nst = 1;

    for(int r = 1; r <= row ; r++) {
        for(int csp = 1; csp <= nsp ; csp++) {
            cout<<"\t";
        }
        cout<<"*\t";
        nsp++;
        cout<<endl;
    }
}

void printPattern7_mathod2(int row) {
    for(int r = 1; r<=row; r++) {
        for(int c = 1; c <= row ; c++) {
            if(r==c) {
                cout<<"*\t";
            }else {
                cout<<"\t";
            }
        }

        cout<<endl;
    }
}
void printPAttern8(int row) {
    int nsp = row - 1;
    int nst = 1;

    for (int r = 1; r <= row; r++) {
        // space
        for (int csp = 1; csp <= nsp; csp++) {
            cout<<("\t");
        }

        // star
        cout<<("*\t");

        nsp--;
        cout<<endl;
    }
}

void pattern8_mathod2(int row) {
    for (int r = 1; r <= row; r++) {
        for (int c = 1; c <= row; c++) {
            if(r+c==row+1) {
                cout<<("*\t");
            }else {
                cout<<("\t");
            }
        }

        cout<<endl;
    }
}

void pattern9(int row) {
    for(int r = 1; r <= row; r++) {
        for(int c = 1; c <= row; c++) {
            if(r == c || r+c == row+1) {
                cout<<("*\t");
            }else {
                cout<<("\t");
            }
        }
        cout<<endl;
    }
}

void pattern10(int row) {
    int outerNSP = row/2; //outer No. of space
    int innerNSP = -1; //inner No. Of Space

    for(int r = 1; r <= row; r++) {
        // space
        for(int csp = 1; csp <= outerNSP; csp++) {
            cout<<("\t");
        }

        // star
        cout<<("*\t");

        // space
        for(int csp = 1; csp <= innerNSP; csp++) {
            cout<<("\t");
        }

        // star
        if(innerNSP != -1) {
            cout<<("*\t");
        }

        // change
        if(r <= row/2) {
            outerNSP--;
            innerNSP+=2;
        }else {
            outerNSP++;
            innerNSP-=2;
        }

        // enter
        cout<<endl;
    }
}

void pattern11(int row) {
    int nst = 1;
    int count = 1;
    for(int r = 1; r <= row; r++) {
        for(int cst = 1; cst <= nst; cst++) {
            cout<<(count) << "\t";
            count++;
        }

        nst++;
        
        cout<<endl;
    }
}

void pattern12(int row) {
    int nst = 1;
    int a = 0;
    int b = 1;
    int c;
    for(int r = 1; r <= row; r++) {
        for(int cst = 1; cst <= nst; cst++) {
            cout<<(a)<<"\t";
            c = a+b;
            a = b;
            b = c;
        }
            
        nst++;
            
        cout<<endl;
    }
}


void pattern13(int row) {

    for (int n = 0; n < row; n++) {
        int val = 1;
        for (int r = 0; r <= n; r++) {
            cout<<(val) << "\t";
            val = ((n - r) * val) / (r + 1);
        }

        cout<<endl;
    }
}

void pattern14(int num) {

    for (int i = 1; i <= 10; i++) {
        cout<< num << " * " << i << " = " << num * i <<endl;
    }
}

void pattern16(int row) {
    int nst = 1;
    int nsp = 2*row - 3;
    
    for (int r = 1; r <= row; r++) {

        // int count = 1;
        // star
        for (int cst = 1; cst <= nst; cst++) {
            cout<< cst << "\t";
        }

        // space
        for (int csp = 1; csp <= nsp; csp++) {
            cout<<("\t");
        }

        if(nsp == -1) {
            nst--;
        }
        // star
        for (int cst = nst; cst >= 1; cst--) {
            cout<<cst << "\t";
        }

        nst++;
        nsp-=2;
        cout<<endl;
    }


}
int main() {
    // printSquare(7);
    // printTriangle(5);
    // printMirrorTriangle(5);
    // printInvertedTriagle(5);
    // printDiamond(7);
    // mirrorInvertedTriangle(7);
    // printHollow(5);
    // pattern15_mathod1(7);
    // printPattern15_mathod2(5);
    // printPattern7(5);
    // printPAttern8(7);
    // printPattern8_mathod2(5);
    // pattern8_mathod2(8);
    // pattern9(5);
    // pattern10(5);
    // pattern11(5);
    // pattern12(5);
    // pattern13(6);
    // pattern14(3);
    pattern16(7);
    return 0;

}