package base;

class SuperClass
{
    SuperClass(String str){
        System.out.println("Super with a String");
    }
}

public class SubClass extends SuperClass{
    SubClass(String str){
        super("");
        System.out.println("Sub with a String");
    }
    public static void main(String[] args){
        SubClass sub = new SubClass("sub");
    }
}