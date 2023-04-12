package Programmers.LV1;

import java.util.HashMap;

public class 달리기_경주 {
    public static String[] solution(String[] players, String[] callings) {

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }

        for (String player: callings) {
            int idx = map.get(player);
            String prevPlayer = players[idx - 1];

            players[idx - 1] = player;
            players[idx] = prevPlayer;

            map.put(player, idx - 1);
            map.put(prevPlayer, idx);
        }

        return players;
    }

    public static void main(String[] args) {
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};


        String[] answer = solution(players, callings);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }

    }
}
