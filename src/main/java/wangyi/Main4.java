package wangyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 4 3
 * 1 2 3 4
 * 4
 * 3
 * 1
 */
public class Main4 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int q = sc.nextInt();
            int[] arr = new int[n];
            int[] arr2 = new int[q];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            for(int i = 0; i < q; i++){
                arr2[i] = sc.nextInt();
            }
            List<Integer> list = solution(arr,arr2);
            for(Integer num : list){
                System.out.println(num);
            }
        }
        private static List<Integer> solution(int[] arr1, int[] arr2){
            ArrayList<Integer> list = new ArrayList<>();
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            for(int i = 0; i < arr2.length; i++){
                int count = 0;
                for(int j = 0; j < arr1.length; j++){
                    if(arr1[j]>=arr2[i]){

                    }
                }
                list.add(count);
            }
            return list;
        }















    }

