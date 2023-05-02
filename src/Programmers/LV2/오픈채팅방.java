package Programmers.LV2;
import java.util.*;

public class 오픈채팅방 {
    public String[] solution(String[] record) {

        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> history = new ArrayList<>();

        for (int i = 0; i < record.length; i++) {
            String[] r = record[i].split(" ");

            // r[0] : command, r[1] : id, r[2] : nickname

            if (r[0].equals("Enter")) {
                map.put(r[1], r[2]);
                history.add("E " + r[1]);
            } else if (r[0].equals("Leave")) {
                history.add("L " + r[1]);
            } else if (r[0].equals("Change")) {
                map.put(r[1], r[2]);
            }
        }

        String[] answer = new String[history.size()];
        for (int i = 0; i < history.size(); i++) {
            String[] a = history.get(i).split(" ");
            StringBuilder sb = new StringBuilder();

            if (a[0].equals("E")) {
                sb.append(map.get(a[1])).append("님이 들어왔습니다.");
            } else if (a[0].equals("L")) {
                sb.append(map.get(a[1])).append("님이 나갔습니다.");
            }
            answer[i] = sb.toString();
        }

        return answer;
    }
}
