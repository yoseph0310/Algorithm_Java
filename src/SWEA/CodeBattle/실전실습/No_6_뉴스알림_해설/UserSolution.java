package SWEA.CodeBattle.실전실습.No_6_뉴스알림_해설;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class UserSolution {

    static class User {
        List<News> newsList;

        public User() {
            this.newsList = new ArrayList<>();
        }
    }

    static class Channel {
        List<Integer> userIdxList;

        public Channel() {
            this.userIdxList = new ArrayList<>();
        }
    }

    static class News implements Comparable<News> {
        boolean isValid;
        int alarmTime;
        int newsID;
        int channelID;

        public News() {
            this.isValid = false;
        }

        @Override
        public int compareTo(News n) {
            if (this.alarmTime == n.alarmTime) return this.newsID - n.newsID;
            else return this.alarmTime - n.alarmTime;
        }
    }

    static final int MAX_USER_SIZE = 500;
    static final int MAX_CHANNEL_SIZE = 500;
    static final int MAX_NEWS_SIZE = 30_000;

    static int N, K;

    // 유저
    static User[] userPool;
    static int userPoolLastIdx;
    static HashMap<Integer, Integer> userHM;

    // 채널
    static Channel[] channelPool;
    static int channelPoolLastIdx;
    static HashMap<Integer, Integer> channelHM;

    // 뉴스
    static News[] newsPool;
    static int newsPoolLastIdx;
    static HashMap<Integer, Integer> newsHM;

    // 뉴스 제공 PQ
    static PriorityQueue<News> newsPQ;

    void init(int n, int k) {
        N = n;
        K = k;

        // 유저 풀 & 해시맵
        userPoolLastIdx = 0;
        userPool = new User[MAX_USER_SIZE];
        for (int i = 0; i < MAX_USER_SIZE; i++) {
            userPool[i] = new User();
        }
        userHM = new HashMap<>();

        // 채널 풀 & 해시맵
        channelPoolLastIdx = 0;
        channelPool = new Channel[MAX_CHANNEL_SIZE];
        for (int i = 0; i < MAX_CHANNEL_SIZE; i++) {
            channelPool[i] = new Channel();
        }
        channelHM = new HashMap<>();

        // 뉴스 풀 & 해시맵
        newsPoolLastIdx = 0;
        newsPool = new News[MAX_NEWS_SIZE];
        for (int i = 0; i < MAX_NEWS_SIZE; i++) {
            newsPool[i] = new News();
        }
        newsHM = new HashMap<>();

        // 뉴스 PQ
        newsPQ = new PriorityQueue<>();
    }

    void alarmNews(int mTime) {
        while (!newsPQ.isEmpty()) {
            News cur = newsPQ.peek();

            if (cur.alarmTime > mTime) return;

            newsPQ.poll();

            if (!cur.isValid) continue;

            for (int i = 0; i < channelPool[cur.channelID].userIdxList.size(); i++) {
                int userIdx = channelPool[cur.channelID].userIdxList.get(i);
                userPool[userIdx].newsList.add(cur);
            }
        }
    }

    void registerUser(int mTime, int mUID, int mNum, int[] mChannelIDs) {
        alarmNews(mTime);

        int userIdx;
        if (!userHM.containsKey(mUID)) {
            userIdx = userPoolLastIdx++;
            userHM.put(mUID, userIdx);
        } else {
            userIdx = userHM.get(mUID);
        }

        for (int i = 0; i < mNum; i++) {
            int channelIdx;

            if (!channelHM.containsKey(mChannelIDs[i])) {
                channelIdx = channelPoolLastIdx++;
                channelHM.put(mChannelIDs[i], channelIdx);
            } else {
                channelIdx = channelHM.get(mChannelIDs[i]);
            }

            channelPool[channelIdx].userIdxList.add(userIdx);
        }
    }

    int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID) {
        int channelIdx = channelHM.get(mChannelID);
        int newsIdx = newsPoolLastIdx++;
        newsHM.put(mNewsID, newsIdx);

        newsPool[newsIdx].alarmTime = mTime + mDelay;
        newsPool[newsIdx].newsID = mNewsID;
        newsPool[newsIdx].channelID = channelIdx;
        newsPool[newsIdx].isValid = true;

        newsPQ.add(newsPool[newsIdx]);

        return channelPool[channelIdx].userIdxList.size();
    }

    void cancelNews(int mTime, int mNewsID) {
        newsPool[newsHM.get(mNewsID)].isValid = false;
    }

    int checkUser(int mTime, int mUID, int[] mRetIDs) {
        alarmNews(mTime);

        int userIdx = userHM.get(mUID);
        int newsCnt = userPool[userIdx].newsList.size();
        int cnt = 0;

        for (int i = userPool[userIdx].newsList.size() - 1; i >= 0; i--) {
            if (!userPool[userIdx].newsList.get(i).isValid) newsCnt--;
            else if (cnt < 3) {
                mRetIDs[cnt++] = userPool[userIdx].newsList.get(i).newsID;
            }
        }

        userPool[userIdx].newsList.clear();

        return newsCnt;
    }
}
