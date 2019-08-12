package com.ck.bytedance;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N-1][2];
        for (int i = 0; i < N-1; i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        int[] arr2 = new int[2*(N-1)-1];
        int i = 0;
        int j = 0;
        while (i<arr2.length-2){
            arr2[i++] = arr[j][1]-arr[j][0];
            j++;
            arr2[i++] = arr[j][0]-arr[j-1][1];
        }
        int count0 = 0,count1 = 1, count2 = 2;
        for (int k = 0; k < arr2.length; k++){
            //if (arr2[k]%3==0){count0++;}
            if (arr2[k]%3==1){count1++;}
            if (arr2[k]%3==2){count2++;}
        }
        System.out.println(count0+" "+count1+" "+count1);
    }
}
