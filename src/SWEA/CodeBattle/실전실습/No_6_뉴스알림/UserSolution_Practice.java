package SWEA.CodeBattle.실전실습.No_6_뉴스알림;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class UserSolution_Practice {

    static class Channel {
        List<Integer> userIdxList;

        public Channel() {
            this.userIdxList = new ArrayList<>();
        }
    }

    static class User {
        List<News> newsList;

        public User() {
            this.newsList = new ArrayList<>();
        }
    }

    static class News implements Comparable<News> {
        boolean isCancel;
        int newsID, channelID, alarmTime;

        public News() {}

        public News(boolean isCancel, int newsID, int channelID, int alarmTime) {
            this.isCancel = false;
            this.newsID = newsID;
            this.channelID = channelID;
            this.alarmTime = alarmTime;
        }

        @Override
        public int compareTo(News n) {
            if (n.alarmTime == this.alarmTime) return this.newsID - n.newsID;
            else return this.alarmTime - n.alarmTime;
        }
    }

    static final int MAX_CHANNEL_SIZE = 500;
    static final int MAX_USER_SIZE = 500;
    static final int MAX_NEWS_SIZE = 30_000;

    static int N, K;

    // 채널 관련
    static Channel[] channelPool;
    static HashMap<Integer, Integer> channelHM;
    static int channelPoolLastIdx;

    // 유저 관련
    static User[] userPool;
    static HashMap<Integer, Integer> userHM;
    static int userPoolLastIdx;

    // 뉴스 관련
    static News[] newsPool;
    static HashMap<Integer, Integer> newsHM;
    static int newsPoolLastIdx;

    static PriorityQueue<News> newsPQ;


    void init(int n, int k) {
        N = n;
        K = k;

        // 채널 관련
        channelPool = new Channel[MAX_CHANNEL_SIZE];
        for (int i = 0; i < MAX_CHANNEL_SIZE; i++) {
            channelPool[i] = new Channel();
        }
        channelPoolLastIdx = 0;
        channelHM = new HashMap<>();

        // 유저 관련
        userPool = new User[MAX_USER_SIZE];
        for (int i = 0; i < MAX_USER_SIZE; i++) {
            userPool[i] = new User();
        }
        userPoolLastIdx = 0;
        userHM = new HashMap<>();

        // 뉴스 관련
        newsPool = new News[MAX_NEWS_SIZE];
        for (int i = 0; i < MAX_NEWS_SIZE; i++) {
            newsPool[i] = new News();
        }
        newsPoolLastIdx = 0;
        newsHM = new HashMap<>();
        newsPQ = new PriorityQueue<>();
    }

    static void alarmTime(int mTime) {
        while (!newsPQ.isEmpty()) {
            News curNews = newsPQ.peek();

            // 현재 뉴스 시간이 크면 아직 알림 시간이 아닌 것.
            if (curNews.alarmTime > mTime) return;

            newsPQ.poll();

            // 현재 뉴스가 취소된 뉴스면 다음 뉴스 확인
            if (curNews.isCancel) continue;

            int channelID = curNews.channelID;

            // 채널 id 로 채널을 조회하고 그 채널의 유저들의 뉴스리스트에 현재 뉴스를 추가한다.
            Channel channel = channelPool[channelID];
            for (int i = 0; i < channel.userIdxList.size(); i++) {
                // 이 채널을 구독하고 있는 유저에게 뉴스를 제공한다.
                int userIdx = channel.userIdxList.get(i);
                userPool[userIdx].newsList.add(curNews);
            }
        }
    }

    void registerUser(int mTime, int mUID, int mNum, int[] mChannelIDs) {
        // mTime 이 되면 유저에 알림 추가하는 메소드
        alarmTime(mTime);

        // 유저 아이디 인덱싱
        // 유저가 아직 등록되지 않았다면
        int userIdx;
        if (!userHM.containsKey(mUID)) {
            userIdx = userPoolLastIdx++;
            userHM.put(mUID, userIdx);
        } else {
            userIdx = userHM.get(mUID);
        }

        // 채널 아이디 인덱싱
        int channelIdx;
        for (int i = 0; i < mNum; i++) {
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
        // 뉴스 인덱싱
        int newsIdx;
        if (!newsHM.containsKey(mNewsID)) {
            newsIdx = newsPoolLastIdx++;
            newsHM.put(mNewsID, newsIdx);
        } else {
            newsIdx = newsHM.get(mNewsID);
        }

        // 채널 정보 가져오기
        int channelIdx = channelHM.get(mChannelID);
        Channel channel = channelPool[channelIdx];

        newsPool[newsIdx].isCancel = false;
        newsPool[newsIdx].newsID = mNewsID;
        newsPool[newsIdx].channelID = channelIdx;
        newsPool[newsIdx].alarmTime = mTime + mDelay;

        newsPQ.add(newsPool[newsIdx]);

        return channel.userIdxList.size();
    }

    void cancelNews(int mTime, int mNewsID) {
        // mNewsID 의 뉴스를 취소한다.
        int newsIdx = newsHM.get(mNewsID);
        newsPool[newsIdx].isCancel = true;
    }

    int checkUser(int mTime, int mUID, int[] mRetIDs) {
        alarmTime(mTime);

        int userIdx = userHM.get(mUID);
        User user = userPool[userIdx];
        int newsCnt = user.newsList.size();
        int cnt = 0;

        for (int i = user.newsList.size() - 1; i >= 0; i--) {
            News news = user.newsList.get(i);
            if (news.isCancel) newsCnt--;

            else if (cnt < 3) {
                mRetIDs[cnt++] = news.newsID;
            }
        }

        user.newsList.clear();

        return newsCnt;
    }
}
