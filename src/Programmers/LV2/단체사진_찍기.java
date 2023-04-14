package Programmers.LV2;

public class 단체사진_찍기 {

    static int answer;
    static String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};

    static int solution(int n, String[] data) {
        answer = 0;
        boolean[] visited = new boolean[8];
        dfs("", visited, data);
        return answer;
    }

    static void dfs(String names, boolean[] visited, String[] datas) {
        if (names.length() == 8) {
            if (check(names, datas)) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String name = names + friends[i];
                dfs(name, visited, datas);
                visited[i] = false;
            }
        }
    }

    static boolean check(String names, String[] datas) {
        for (String data: datas) {
            int pos1 = names.indexOf(data.charAt(0));
            int pos2 = names.indexOf(data.charAt(2));
            char op = data.charAt(3);
            int idx = data.charAt(4) - '0';

            int dist = Math.abs(pos1 - pos2);
            if (op == '=') {
                if (!(dist == idx + 1)) return false;
            } else if (op == '<') {
                if (!(dist < idx + 1)) return false;
            } else if (op == '>') {
                if (!(dist > idx + 1)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n1 = 2;
        int n2 = 2;

        String[] data1 = {"N~F=0", "R~T>2"};
        String[] data2 = {"M~C<2", "C~M>1"};

        System.out.println(solution(n1, data1));
        System.out.println(solution(n2, data2));
    }
}
