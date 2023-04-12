package Programmers.LV1;

import java.util.*;

public class 바탕화면_정리 {
    char[][] board;

    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = 0;
    int maxY = 0;

    public int[] solution(String[] wallpaper) {
        board = new char[wallpaper.length][wallpaper[0].length()];

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                char c = wallpaper[i].charAt(j);

                if (c == '#') {
                    makeMin(i, j);
                    makeMax(i, j);
                }

                board[i][j] = c;
            }
        }

        maxX++;
        maxY++;
        int[] answer = {minX, minY, maxX, maxY};
        return answer;
    }

    void makeMin(int x, int y) {
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
    }

    void makeMax(int x, int y) {
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
    }

}