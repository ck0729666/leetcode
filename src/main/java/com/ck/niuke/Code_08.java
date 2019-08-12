package com.ck.niuke;

import java.util.PriorityQueue;

//荷兰国旗
public class Code_08 {
    public static int[] partition(int[] arr, int L, int R, int num){
        int less = L-1;
        int more = R+1;
        int curr = L;
        while (curr<more){
            if(arr[curr]<num){
                 swap(arr,++less,curr++);
            }else if(arr[curr]>num){
                swap(arr,curr,--more);
            }else{
                curr++;
            }
        }
        return new int[]{less+1,more-1};
    }
    //改进快排
    public static int[] partition2(int[] arr, int L, int R){
        int less = L-1;
        int more = R;
        while (L<more){
            if(arr[L]<arr[R]){
                swap(arr,++less,L++);
            }else if(arr[L]>arr[R]){
                swap(arr,--R,L);
            }else {
                L++;
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }
    public static void quickSort(int[] arr,int L,int R){
         if(L<R) {
             //随机快排,L到R上随机选个数和R上交换
             //swap(arr,L+(int)(Math.random()*(R-L+1)),R);
            int[] p = partition2(arr,L,R);
            quickSort(arr,L,p[0]-1);
            quickSort(arr,p[1]+1,R);
         }
    }
    //归并排序
    public static void merge(int[] arr, int L, int mid, int R){
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1<=mid && p2<=R){
            help[help[i++]]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[i++]=arr[p1++];
        }
        while (p2<=R){
            help[i++]=arr[p2++];
        }
        for(i = 0; i < help.length; i++){
            arr[L+i] = help[i];
        }
    }
    //堆排序
    public static void heapSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        //0~i建堆，时间复杂度o(n)=log1+...+logN-1 = o(N)
        for(int i  = 0; i < arr.length; i++){
            heapInsert(arr,i);
        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while (heapSize>0){
            heapify(arr,0, heapSize);
            swap(arr,0, --heapSize);
        }
    }
    //往上跑的过程（例子为大根堆）
    public static void heapInsert(int[] arr, int index){
        //如果大于父节点，while循环一直与父亲交换
        while (arr[index]>arr[(index-1)/2]){
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }
    public static void modify(int[] arr, int index, int heapsize){
        int left = index*2+1;
        while (left<heapsize){
            int largest = (left+1)<heapsize && arr[left]<arr[left+1] ? left+1 : left;
            largest = arr[largest]>arr[index] ? largest : index;
            if(largest==index){
               break;
            }
            swap(arr,largest,index);
            index = largest;
            left = 2*index+1;
        }
    }
    public static void heapsort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        //建堆
        for (int i = 0; i < arr.length; i++){
            heapInsert(arr,i);
        }
        int heapSize = arr.length;
        swap(arr, --heapSize, 0);
        while (heapSize>0){
            heapify(arr, heapSize, 0);
            swap(arr,--heapSize, 0);
        }
    }
    public static void heapsort2(int[] arr){
        if(arr==null||arr.length <2){
            return;
        }
        for(int i = 0; i < arr.length; i++){
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr,--heapSize,0);
        while (heapSize>0){
            heapify(arr,heapSize,0);
            swap(arr,--heapSize,0);
        }
    }

    public static boolean isPalindrome3(ListNode head){
        if(head==null||head.next==null){
            return true;
        }
        ListNode n1=head;
        ListNode n2=head;
        //find mid node奇来到中间；偶数来到中间的两个的前面一个
        while (n2.next!=null&&n2.next.next!=null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        //n2来到右边的第一个node
        n2 = n1.next;
        n1.next = null;
        ListNode n3 = null;
        //后半部分逆序，连到中点部分
        while (n2!=null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        //保存最后一个指针
        n3=n1;
        //左边第一个指针
        n2=head;
        boolean res=true;
        while (n2!=null&&n1!=null){
          if(n1.val!=n2.val){
              res = false;
              break;
          }
          n1 = n1.next;
          n2 = n2.next;
        }
        //把右半部分调回来
        n1 = n3.next;
        n3.next=null;
        while (n1!=null){
            n2 = n1.next;
            n1.next = n2;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
    //冒泡排序
    public static void BubbleSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++){//外层循环控制排序次数->n次
            for(int j = 0; j < arr.length-1-i;j++){//内层循环控制每次循环交换多少次
                if(arr[j]>arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,3,8,2,9,1};
        BubbleSort(arr);
        for(int i = 0; i < arr.length; i ++){
            System.out.print(" " + arr[i]);
        }
    }








    //一个值变小往下沉的操作
    //index是父节点索引，heapSize判断是否越界
    public static void heapify(int[] arr, int index, int heapSize){
        int left = index*2+1;
        while (left<heapSize){
            int largest = left+1<heapSize&&arr[left+1]>arr[left] ? left+1 : left;
            largest = arr[largest]>arr[index] ? largest:index;
            if(largest==index){
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = 2*index+1;
        }
    }

































































    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




















}
