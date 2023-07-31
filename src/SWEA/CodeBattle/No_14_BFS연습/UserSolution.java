package SWEA.CodeBattle.No_14_BFS연습;

public class UserSolution {

    static int N;
    static int[][] board;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    /**
     *
     * @param map_size      : 지도의 크기 1 <= map_size <= 10
     * @param map           : map 은 0 (길) 과 1 (벽) 로 주어짐
     */
    void bfs_init(int map_size, int map[][]) {
        N = map_size;
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = map[i][j];
            }
        }
    }

    int bfs(int x1, int y1, int x2, int y2) {
        Queue<Point> q = new Queue<>();
        boolean[][] visited = new boolean[N][N];

        int cnt = 0;

        q.add(new Point(y1-1, x1-1));
        visited[y1-1][x1-1] = true;

        while (q.size() != 0) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Point cur = q.poll();

                if (cur.y == y2-1 && cur.x == x2-1) {
                    return cnt;
                }

                for (int d = 0; d < 4; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                    if (visited[ny][nx]) continue;
                    if (board[ny][nx] == 1) continue;

                    q.add(new Point(ny, nx));
                    visited[ny][nx] = true;
                }
            }

            cnt++;
        }

        return -1;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Node<E> {
        E data;
        Node<E> next;

        Node (E data) {
            this.data = data;
            this.next = null;
        }
    }

    static class Queue<E> {
        private Node<E> head;
        private Node<E> tail;
        private int size;

        public Queue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public int size() {
            return size;
        }

        public void addFirst(E value) {
            Node<E> newNode = new Node<>(value);
            newNode.next = head;
            head = newNode;
            size++;

            if (head.next == null) {
                tail = head;
            }
        }

        public void addLast(E value) {
            Node<E> newNode = new Node<>(value);

            if (size == 0) {
                addFirst(value);
                return;
            }

            tail.next = newNode;
            tail = newNode;
            size++;
        }

        public boolean add(E value) {
            addLast(value);
            return true;
        }

        public E poll() {
            Node<E> headNode = head;

            E element = headNode.data;

            Node<E> nextNode = head.next;

            head.data = null;
            head.next = null;

            head = nextNode;
            size--;

            if (size == 0) {
                tail = null;
            }

            return element;
        }
    }
}

