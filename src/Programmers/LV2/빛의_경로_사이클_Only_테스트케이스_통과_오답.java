package Programmers.LV2;
import java.util.*;

public class 빛의_경로_사이클_Only_테스트케이스_통과_오답 {

    int res;
    List<Path> pathList;

    // 상 우 하 좌
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public ArrayList<Integer> solution(String[] grid) {
        ArrayList<Integer> answer = new ArrayList<>();
        pathList = new ArrayList<>();

        for (int d = 0; d < 4; d++) {
            res = 0;
            // 사이클이 가능하면
            if (isPossibleCycle(grid, 0, 0, d)) {
                answer.add(res);
            }
            // 사이클이 가능하지 않으면
            else {
                continue;
            }
        }

        return answer;
    }
    // 사이클이 가능한지 판단 - grid : 격자 / x, y : 좌표 / dir : 현재 이동방향
    boolean isPossibleCycle(String[] grid, int x, int y, int dir) {

        // 1. 다음 목적지 정보 (방향) - 현재 좌표의 값('S', 'L', 'R') 에 따라 다음 방향이 바뀐다.
        int ndir = dir;
        if (grid[x].charAt(y) == 'L') {
            ndir = (dir + 3) % 4;
        } else if (grid[x].charAt(y) == 'R') {
            ndir = (dir + 1) % 4;
        }

        // 2. 다음 목적지 정보 (좌표) - 다음 좌표가 경계선이라면 반대편으로 이동한다.
        int nx = x + dx[ndir];
        int ny = y + dy[ndir];

        if (nx < 0) nx = grid.length - 1;
        if (nx >= grid.length) nx = 0;
        if (ny < 0) ny = grid[0].length() - 1;
        if (ny >= grid[0].length()) ny = 0;

        // 현재(출발점 (x,y,dir))와 다음 목적지(도착점 (nx, ny, ndir)) 을 나타내는 Path를 하나 만든다.
        Point departure = new Point(x, y);
        Point destination = new Point(nx, ny);
        Path np = new Path(departure, destination, ndir);

        boolean isP = true;

        for (Path p : pathList) {
            if (p.departure.x == np.departure.x && p.departure.y == np.departure.y
                    && p.destination.x == np.destination.x && p.destination.y == np.destination.y
                    && p.dir == np.dir) {
                isP = false;
                return isP;
            }
        }
        // list에 없거나 path가 같지 않으면 res를 1 증가시키고 list에 Path를 넣는다. 다음 목적지에 대한 확인을 한다.
        res++;
        pathList.add(np);
        isPossibleCycle(grid, nx, ny, ndir);

        return isP;
    }

    class Path {
        Point departure;
        Point destination;
        int dir;

        public Path(Point departure, Point destination, int dir) {
            this.departure = departure;
            this.destination = destination;
            this.dir = dir;
        }
    }

    class Point {
        int x, y, dir;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
