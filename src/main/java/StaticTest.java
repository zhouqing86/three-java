public class StaticTest {
    public static void main(String[] args) {
        Base b = new Sub();
        System.out.println(b.x);
        System.out.println(b.getX());
        int c = 2;
        int d = 5;
        int e = -2;
        System.out.println(++c+d+++e++);

    }

    public static class Sub extends Base{
        int x;

        public Sub(){
            foo(1);
            this.x = 2;
        }

        public void foo(int i) {
            this.x = i;
            System.out.println("Sub: " + this.x);
        }

        public int getX() {
            return this.x;
        }
    }


    public static class Base {
        int x;

        public Base(){
            foo(3);
            this.x = 4;
        }

        public void foo(int i) {
            this.x = i;
            System.out.println("Base: " + this.x);
        }

        public int getX() {
            return this.x;
        }

    }
}
