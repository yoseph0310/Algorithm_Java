package Programmers.LV2;

import java.util.LinkedList;
import java.util.Queue;

public class 프로세스 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Printer> q = new LinkedList<>();

        for(int i = 0; i < priorities.length; i++) {
            q.offer(new Printer(i, priorities[i]));
        }

        while(!q.isEmpty()){
            boolean flag = false;
            int a = q.peek().priority;

            for (Printer p : q){
                if ( a < p.priority){
                    flag = true;
                }
            }

            if(flag){
                q.offer(q.poll());
            }else{
                if ( q.poll().location == location){
                    answer = priorities.length - q.size();
                }
            }
        }
        return answer;
    }
    class Printer {
        int location;
        int priority;

        public Printer(int location, int priority){
            this.location = location;
            this.priority = priority;
        }
    }
}
