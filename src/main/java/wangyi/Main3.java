package wangyi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 10
 * 53941 38641 31525 75864 29026 12199 83522 58200 64784 80987
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        solution(arr,n);
        for(int i = 0; i < n; i++){
            System.out.print(arr[i]+" ");
        }
    }
    private static void solution(int[] arr, int n){
        int count = 0;
        for (int i = 0; i < n; i++){
            if (arr[i]%2==0){
                count++;
            }
        }
        if (count!=n&&count!=0){
            Arrays.sort(arr);
        }
    }

















}
