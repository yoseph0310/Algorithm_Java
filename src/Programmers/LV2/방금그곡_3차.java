package Programmers.LV2;

public class 방금그곡_3차 {
    public String solution(String m, String[] musicinfos) {
        int maxPlayTime = -1;
        String answer = "";

        m = makeNewMelody(m);

        for (String musicInfo : musicinfos) {
            String[] info = musicInfo.split(",");
            String title = info[2];
            // 악보 반음 처리
            String melodyInfo = makeNewMelody(info[3]);

            String[] timeInfo = info[0].split(":");
            // 시작 시간 분단위로 변환
            int start = Integer.valueOf(timeInfo[0]) * 60 + Integer.valueOf(timeInfo[1]);

            // 끝난 시간 분단위로 변환
            timeInfo = info[1].split(":");
            int end = Integer.valueOf(timeInfo[0]) * 60 + Integer.valueOf(timeInfo[1]);

            // 실행 시간 구함
            int play = end - start;

            // 음악 길이보다 실행 시간이 길 때
            if (play > melodyInfo.length()) {
                StringBuilder newMelody = new StringBuilder();

                // 나눈 몫 만큼 처음부터 반복
                for (int i = 0; i < play / melodyInfo.length(); i++) {
                    newMelody.append(melodyInfo);
                }

                // 나머지만큼 악보에서 잘라 붙임
                newMelody.append(melodyInfo.substring(0, play % melodyInfo.length()));
                melodyInfo = newMelody.toString();

            } else {
                melodyInfo = melodyInfo.substring(0, play);
            }

            if (melodyInfo.contains(m) && play > maxPlayTime) {
                answer = title;
                maxPlayTime = play;
            }

        }

        return maxPlayTime != -1 ? answer : "(None)";
    }

    String makeNewMelody(String m) {
        m = m.replaceAll("C#", "Q");
        m = m.replaceAll("D#", "W");
        m = m.replaceAll("F#", "R");
        m = m.replaceAll("G#", "T");
        m = m.replaceAll("A#", "Y");

        return m;
    }
}
