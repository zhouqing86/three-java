package algorithm;

import java.util.Arrays;

public class BubbleSortTest {
    public static void main(String[] args) {
        int a[] = {3, 16, 7, 2, 10, 2};
        int tmp = 0;
        for(int i=0; i<a.length; i++) {
            for(int j=0; j<a.length-1-i; j++) {
                if(a[j]>a[j+1]){
                    tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1]=tmp;
                }
            }
        }

        Arrays.stream(a).forEach(System.out::println);
    }
}
