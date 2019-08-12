package com.ck.bytedance;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr1 = new int[N][2];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < 2; j++){
                arr1[i][j] = sc.nextInt();
            }
        }
        int X = sc.nextInt();
        int[][] arr2 = new int[1][2];
        arr2[0][0] = sc.nextInt();
        arr2[0][1] = sc.nextInt();
        int[][] ans = new int[1][2];
        //double result = 0;
        for (int i = 0; i < N; i++){
            int min = arr1[i][1]+X;
            if (min >= 60){
                arr1[i][0] += min/60;
                arr1[i][1] = min%60;
            }else {
                arr1[i][1] = min;
            }
            if (arr1[i][0]<arr2[0][0] || (arr1[i][0]==arr2[0][0]&&arr1[i][1]<=arr2[0][1])){
                if ((arr1[i][0]+arr1[i][1]*0.01)>(ans[0][0]+ans[0][1]*0.01)) {
                    ans[0][0] = Math.max(arr1[i][0], ans[0][0]);
                    ans[0][1] = Math.max(arr1[i][1], ans[0][1]);
                }
            }
        }
        System.out.println(ans[0][0]+" "+(ans[0][1]-X));
    }








}
