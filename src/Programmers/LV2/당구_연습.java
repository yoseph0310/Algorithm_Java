package Programmers.LV2;
import java.util.*;

public class 당구_연습 {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        Point border = new Point(m, n);
        Point start = new Point(startX, startY);

        for (int i = 0; i < balls.length; i++) {
            int[] ball = balls[i];

            List<Point> availableBall = calcPosition(border, start, new Point(ball[0], ball[1]));

            int minDistance = Integer.MAX_VALUE;
            for (Point point: availableBall) {
                int dis = calcDistance(start, point);

                minDistance = Math.min(minDistance, dis);
            }

            answer[i] = minDistance;
        }

        return answer;
    }

    int calcDistance(Point start, Point ball) {
        int bX = Math.max(start.x, ball.x);
        int sX = Math.min(start.x, ball.x);
        int bY = Math.max(start.y, ball.y);
        int sY = Math.min(start.y, ball.y);

        return (int)Math.pow(bX - sX, 2) + (int)Math.pow(bY - sY, 2);
    }

    List<Point> calcPosition(Point border, Point start, Point ball) {
        List<Point> list = new ArrayList<>();

        // 4 방 대칭 이동
        // 선 대칭일때, 벽보다 공에 맞는 경우는 제외한다.
        if (!(start.x == ball.x && start.y > ball.y)) list.add(new Point(ball.x, ball.y * -1));
        if (!(start.x == ball.x && start.y < ball.y)) list.add(new Point(ball.x, border.y + (border.y - ball.y)));
        if (!(start.y == ball.y && start.x > ball.x)) list.add(new Point(ball.x * - 1, ball.y));
        if (!(start.y == ball.y && start.x < ball.x)) list.add(new Point(border.x + (border.x - ball.x), ball.y));

        return list;
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
