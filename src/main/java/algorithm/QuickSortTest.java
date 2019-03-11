package algorithm;

import java.util.Arrays;

public class QuickSortTest {

    public static void swap(int []arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }


    public static void quickSort(int []arr, int start, int end) {
        if (start >= end || start > arr.length - 1) return;
        int divide = arr[start];
        int i = start, j = end;
        while (i < j) {
            while (arr[i] <= divide) {
                if (i++ == end) break;
            }

            while (arr[j] >= divide) {
                if (j-- == start) break;
            }

            //交换值
            if (i < j) swap(arr, i, j);
        }

        //交换标志位的值与最后一个比它小的值
        swap(arr, start, i-1);
        quickSort(arr, start, i-2);
        quickSort(arr, i, end);
    }

    public static void main(String[] args) {
        int a[] = {2, 16, 7, 3, 10, 3};
        quickSort(a, 0, a.length-1);

        System.out.println("=========================");
        Arrays.stream(a).forEach(System.out::println);

        int b[] = {3, 16, 7, 2, 10, 2};
        quickSort(b, 0, b.length-1);

        System.out.println("=========================");
        Arrays.stream(b).forEach(System.out::println);


        int c[] = {16, 3, 7, 2, 10, 2};
        quickSort(c, 0, c.length-1);

        System.out.println("=========================");
        Arrays.stream(c).forEach(System.out::println);
    }
}
