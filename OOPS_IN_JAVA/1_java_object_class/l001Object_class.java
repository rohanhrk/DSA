import java.util.*;

/*
    class => A class is a template or blueprint from which object are created.
            It contains data, Methods, Constructor, Blocks, Nested class and interface
*/

// How to create class

class l001Object_class {
    /*
        body =>
        public class <class_name> {
            data;
            method;
        }
    */ 

    // student class 
    public static class Student {

        // data
        String name; 
        int roll_number;

        // method
        void printDetais() {
            System.out.println(this.name + " " + this.roll_number);
        }
    }

    public static void main(String[] args) {
        /* 
            Object => Object is an instance of class which is created at runtime. 
        */ 
        // How to create an object or instance
        Student s1 = new Student(); // creating an object of student

        // priting values of the object by method call
        s1.printDetais();
    }
}

/*
    => 1. new keyword => The new keyword is used to allocate memory at runtime. All objects get memory in Heap memory area.
    => 2. there are 3 ways to initialize object => a) By referanced variable b) By mathod c) By constructor 

*/ 


