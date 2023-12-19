package Programmers.LV3;
import java.util.*;

public class 베스트앨범_재풀이 {
    class Solution {

        HashMap<String, Genre> genresHM;
        PriorityQueue<Genre> genresPQ;     // 전체 재생횟수로 정렬되는 장르 PQ

        public List<Integer> solution(String[] genres, int[] plays) {
            List<Integer> answer = new ArrayList<>();

            genresHM = new HashMap<>();
            genresPQ = new PriorityQueue<>();

            // 입력으로 주어지는 노래 정보(장르, 재생횟수) 배열의 길이
            int len = genres.length;

            // 장르 해시맵을 통해 장르 별 노래 리스트 수록 정리
            for (int i = 0; i < len; i++) {
                Song newSong = new Song(i, plays[i]);

                // 해시맵에 장르가 없다면
                if (!genresHM.containsKey(genres[i])) {
                    // 해시맵에 장르 추가
                    genresHM.put(genres[i], new Genre(genres[i]));
                }
                // 장르의 PQ 에 노래 추가
                genresHM.get(genres[i]).songPQ.add(newSong);

                // 장르 재생 횟수 증가
                genresHM.get(genres[i]).playTotalCnt += plays[i];
            }

            // 장르 해시맵을 돌면서 totalCnt 를 활용하여 genrePQ 정보 저장
            for (Genre g : genresHM.values()) {
                genresPQ.add(g);
            }

            while (!genresPQ.isEmpty()) {
                Genre curGenre = genresPQ.poll();

                int cnt = 0;
                while (!curGenre.songPQ.isEmpty()) {
                    if (cnt == 2) break;
                    answer.add(curGenre.songPQ.poll().idx);
                    cnt++;
                }
            }

            return answer;
        }

        class Genre implements Comparable<Genre> {
            String name;
            int playTotalCnt;
            PriorityQueue<Song> songPQ;

            public Genre(String name) {
                this.name = name;
                this.playTotalCnt = 0;
                this.songPQ = new PriorityQueue<>();
            }

            @Override
            public int compareTo(Genre g) {
                return g.playTotalCnt - this.playTotalCnt;
            }
        }

        class Song implements Comparable<Song> {
            int idx, playCnt;

            public Song(int idx, int playCnt) {
                this.idx = idx;
                this.playCnt = playCnt;
            }

            @Override
            public int compareTo(Song s) {
                if (s.playCnt == this.playCnt) return this.idx - s.idx;
                else return s.playCnt - this.playCnt;
            }
        }
    }
}
