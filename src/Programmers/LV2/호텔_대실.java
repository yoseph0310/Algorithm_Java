package Programmers.LV2;
import java.util.*;

public class 호텔_대실 {
    public int solution(String[][] book_time) {
        int answer = 0;
        List<Book> book_list = new ArrayList<>();

        // String으로 표현된 시간을 int값으로 변경
        for (int i = 0; i < book_time.length; i++) {
            String start_time = book_time[i][0];
            String end_time = book_time[i][1];

            String[] startTimeInfo = start_time.split(":");
            String[] endTimeInfo = end_time.split(":");

            // minute 단위로 시간을 int 값으로 변환
            int start = Integer.parseInt(startTimeInfo[0]) * 60 + Integer.parseInt(startTimeInfo[1]);
            int end = Integer.parseInt(endTimeInfo[0]) * 60 + Integer.parseInt(endTimeInfo[1]);

            book_list.add(new Book(start, end + 10));
        }

        int min = Integer.MAX_VALUE;
        int max = 0;
        Collections.sort(book_list);
        for(Book b : book_list) {

            min = Math.min(b.start, min);
            max = Math.max(b.end, max);
        }

        int[] time = new int[1500];

        for (int i = 0; i < book_list.size(); i++) {
            Book book = book_list.get(i);

            for (int j = book.start; j < book.end; j++) {
                time[j] += 1;
            }
        }

        for (int i = 0; i < time.length; i++) {
            answer = Math.max(answer, time[i]);
        }

        return answer;
    }

    class Book implements Comparable<Book> {
        int start, end;

        public Book(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Book o) {
            return this.start - o.start;
        }
    }

}
