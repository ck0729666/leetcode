package com.ck.bytedance;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        boolean[] arr2 = new boolean[N];
        for (int i = 0; i < N-1; i++){
            if (arr[i]<arr[i+1]){
                arr2[i]=false;
            }else {
                arr2[i]=true;
            }
        }
        if (arr[N-1]>arr[0]){
            arr2[N-1] = true;
        }else {
            arr2[N-1] = false;
        }
        int i = 0,sum = 100;
        while (i < N-1){
            int count = 1;
            if (arr2[i]==true) {
                while (arr2[i] == true) {
                    if (i>0&&arr2[i-1]==false){
                        sum += count*100;
                    }else {
                        sum += (count + 1) * 100;
                    }
                    count++;
                    i++;
                }
            }else {
                while (arr2[i] == false) {
                    if (i>0&&arr2[i-1]==true){
                        sum += count*100;
                    }else {
                        sum += (count + 1) * 100;
                    }
                    count++;
                    i++;
                }
            }
        }
        if (arr2[N-1]){
            System.out.println(sum);
        }else {
            System.out.println(sum);
        }
    }

















}
