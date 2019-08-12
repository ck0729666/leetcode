package wangyi;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NowCoderWangyi {
    public static void main(String[] args) {
        /**
        String str = "aazz";

        System.out.println(ss(str,1,2));*/
        Scanner sca = new Scanner(System.in);
        int n = sca.nextInt();
        int k = sca.nextInt();
        PriorityQueue<Tower> maxHeap = new PriorityQueue<>((o1,o2)->{return o2.level-o1.level;});
        PriorityQueue<Tower> minHeap = new PriorityQueue<>((o1,o2)->{return o1.level-o2.level;});
        Tower tower = null;
        for(int i=0;i<n;i++){
            int num = sca.nextInt();
            tower = new Tower(i+1,num);//1,5 2,8 3,5
            maxHeap.offer(tower);
            minHeap.offer(tower);
        }
        int times=0;
        int s = 0;
        int[][] move = new int[k][2];
        while (times<k){
            Tower hTower = maxHeap.poll();//2,8
            Tower lTower = minHeap.poll();//1,5
            if(hTower.level-lTower.level<=1){break;}
            lTower.level = lTower.level+1;
            hTower.level = hTower.level-1;
            maxHeap.add(hTower);
            minHeap.add(lTower);
            move[times][0] = hTower.index;
            move[times][1] = lTower.index;
            times++;
        }
        System.out.println(maxHeap.peek().level-minHeap.peek().level+" "+times);
        for(int i = 0; i < times;i++){
            System.out.println(move[i][0]+" "+move[i][1]);
        }
    }







    //aazz aaazz
    public static void solution7(int n, int m, int k){
        char[] chars = new char[n+m];
        for(int i = 0; i < n; i++){
            chars[i] = 'a';
        }
        for(int j = n; j < m+n; j++){
            chars[j] = 'z';
        }
        ArrayList<String> list = new ArrayList<>();
        String str = String.valueOf(chars);
        list.add(str);
        for(int i = n-1;i>0;i--){
            String s = ss(str,n,i);
            list.add(s);
            for(int j = n+m-1;j>n;j--){
                String s2 = ss(s,j,j-1);
                list.add(s2);
            }
        }
        for (String res : list){
            System.out.println(res);
        }
    }
    private static String ss(String str,int i,int j){
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return String.valueOf(chars);
    }

     /**
     * 总分值=原本醒得的分值+叫醒他额外得到的分值。
     * 额外得到的分值：单纯的遍历会超时。
     * 这里用了一个队列的思想，进一个，出一个。

        public static void main(String[] args) {
     *Scanner in = new Scanner(System.in);
     *int n = in.nextInt();
     *int k = in.nextInt();
     *int[] fun = new int[n];
     *int[] sleep = new int[n];
     *int ans = 0;
     *for (int i = 0; i < n; i++) {
     *fun[i] = in.nextInt();
     *}
     *for (int i = 0; i < n; i++) {
     *sleep[i] = in.nextInt();
     *if (sleep[i] == 1) {
     *ans += fun[i];
     *}
     *}
     *int cnt = 0;    //醒来得到的兴趣值。
     *for (int i = 0; i < n && i < k; i++) {
     *if (sleep[i] == 0)
     *cnt += fun[i];
     *}
     *int max = cnt;
     *for (int i = 1; i < n; i++) {
     *if (sleep[i - 1] == 0) cnt -= fun[i - 1];
     *if (i + k - 1 < n && sleep[i + k - 1] == 0)
     *cnt += fun[i + k - 1];
     *max = Math.max(max, cnt);
     *}
     *ans += max;
     *System.out.println(ans);
     *}
     *
    }
     *
     */
     public ListNode middleNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        int count = 0;
        while (p1!=null&&p1.next!=null){
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;
     }














}
class Tower{
    int index;
    int level;
    public Tower(int index, int level){
        this.index = index;
        this.level = level;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}