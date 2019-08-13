package wangyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 1
 * 5
 * 17 6 17 11 17
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int n = sc.nextInt();
        for(int i = 0; i < t; i++){
            int[] arr = new int[n];
            for (int j = 0; j < n; j++){
                arr[j] = sc.nextInt();
            }
            Arrays.sort(arr);
            int temp = arr[n-1];
            arr[n-1] = arr[n-2];
            arr[n-2] = temp;
            for (int k = 0; k < n; k++){
                if (arr[(k-1+n)%n]+arr[(k+1)%n]<=arr[k]){
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

}





















