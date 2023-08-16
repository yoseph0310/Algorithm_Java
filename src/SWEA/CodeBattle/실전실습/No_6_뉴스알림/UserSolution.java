package SWEA.CodeBattle.실전실습.No_6_뉴스알림;

import java.util.*;

/**
 * mTime 은 함수 호출 시 항상 1 씩 증가한다.
 * 모든 유저가 받는 뉴스 알림의 총합 : 최대 4,000,000 개
 * registerUser() 최대 호출 : 5,000
 * offerNews() 최대 호출 : 30,000
 * cancelNews() 최대 호출 : 3,000
 * checkUser() 최대 호출 : 1,000
 */
class UserSolution {

    static final int MAX_USER_SIZE = 500;
    static final int MAX_CHANNEL_SIZE = 500;
    static final int MAX_NEWS_SIZE = 30_000;

    static int N, K;
    // 채널
    static class Channel {
        // 뉴스를 제공할 유저 리스트
        List<Integer> userIdxList;

        public Channel() {
            this.userIdxList = new ArrayList<>();
        }
    }

    // 유저
    static class User {
        // 한 유저가 받은 뉴스를 저장할 자료구조 하나.
        List<News> newsList;

        public User() {
            this.newsList = new ArrayList<>();
        }
    }

    // 뉴스
    static class News implements Comparable<News> {
        boolean isValid;        // 삭제 여부
        int alarmTime;          // 알림 시간 (mTime + delayTime);
        int newsID;             // 뉴스 아이디
        int channelID;          // 채널 아이디

        public News() {
            this.isValid = true;
        }

        @Override
        public int compareTo(News n) {
            // 시간이 같으면 ID 가 큰것
            if (n.alarmTime == this.alarmTime) return this.newsID - n.newsID;
            else return this.alarmTime - n.alarmTime;
        }
    }

    static User[] userPool;         // 최대 유저 수 : 500
    static int userPoolLastIdx;
    static HashMap<Integer, Integer> userHM;

    static News[] newsPool;
    static int newsPoolLastIdx;
    static HashMap<Integer, Integer> newsHM;

    static Channel[] channelsPool;  // 최대 채널 수 : 500
    static int channelsPoolLastIdx;
    static HashMap<Integer, Integer> channelHM;

    static PriorityQueue<News> newsPQ;

    static int Order;

    /**
     * N 명의 유저와 뉴스 알림 서비스를 제공하는 K 개의 뉴스 채널.
     *
     * 현재 시각은 0, 제공된 뉴스는 없다.
     *
     * @param n         : 뉴스 알림을 받는 유저의 수 (1 <= N <= 500)
     * @param k         : 뉴스 알림을 보내는 뉴스 채널의 수 (1 <= K <= 500)
     */
    void init(int n, int k) {
        Order = 1;

        N = n;
        K = k;

        // 유저 풀 & 유저 해시맵
        userPoolLastIdx = 0;
        userPool = new User[MAX_USER_SIZE];
        for (int i = 0; i < MAX_USER_SIZE; i++) {
            userPool[i] = new User();
        }
        userHM = new HashMap<>();

        // 뉴스 풀 & 뉴스 해시맵
        newsPoolLastIdx = 0;
        newsPool = new News[MAX_NEWS_SIZE];
        for (int i = 0; i < MAX_NEWS_SIZE; i++) {
            newsPool[i] = new News();
        }
        newsHM = new HashMap<>();

        // 채널 풀 & 채널 해시맵
        channelsPoolLastIdx = 0;
        channelsPool = new Channel[MAX_CHANNEL_SIZE];
        for (int i = 0; i < MAX_CHANNEL_SIZE; i++) {
            channelsPool[i] = new Channel();
        }
        channelHM = new HashMap<>();

        newsPQ = new PriorityQueue<>();
    }

    static void alarmNews(int mTime) {

        while (!newsPQ.isEmpty()) {
            News cur = newsPQ.peek();

            if (cur.alarmTime > mTime) {
                return;
            }

            newsPQ.poll();

            if (!cur.isValid) {
                continue;
            }

            for (int i = 0; i < channelsPool[cur.channelID].userIdxList.size(); i++) {
                int userIdx = channelsPool[cur.channelID].userIdxList.get(i);
                userPool[userIdx].newsList.add(cur);
            }
        }
    }

    /**
     * mTime 시각에 mUID 유저는 뉴스 알림을 받기 위해 mNum 개의 뉴스 채널 mChannelIDs[] 에 등록
     * mTime 시각에 유저에게 보내지는 뉴스 알림이 있는 경우 먼저 알림을 보낸 후, mUID 유저를 뉴스 채널에 등록
     * mChannelIDs[] 뉴스 채널들은 중복이 없다.
     *
     * mUID 는 중복으로 주어질 수 있지만, 동일 유저에게 등록되는 뉴스 채널은 서로 중복되지 않는다.
     * 유저는 중복 호출 되나 뉴스 채널은 중복되지 않는다.
     *
     * @param mTime             : 현재 시각 (1 <= mTime <= 1,000,000,000)
     * @param mUID              : 유저의 ID (1 <= mUID <= 1,000,000,000)
     * @param mNum              : 유저가 등록하는 뉴스 채널의 수 (1 <= mNum <= 30)
     * @param mChannelIDs       : 유저가 등록하는 뉴스 채널의 ID (1 <= mChannelIds 의 값 <= 1,000,000,000)
     */
    void registerUser(int mTime, int mUID, int mNum, int[] mChannelIDs) {
        alarmNews(mTime);

        // mUID 인덱싱 처리
        int userIdx;
        // 유저풀에 유저가 등록되지 않았다면
        if (!userHM.containsKey(mUID)) {
            userIdx = userPoolLastIdx++;
            userHM.put(mUID, userIdx);
        }
        else {
            userIdx = userHM.get(mUID);
        }

        // mChannelIDs 인덱싱 처리
        for (int i = 0; i < mNum; i++) {
            int channelIdx;

            // 채널풀에 채널이 등록되지 않았다면
            if (!channelHM.containsKey(mChannelIDs[i])) {
                channelIdx = channelsPoolLastIdx++;
                channelHM.put(mChannelIDs[i], channelIdx);
            }
            else {
                channelIdx = channelHM.get(mChannelIDs[i]);
            }

            channelsPool[channelIdx].userIdxList.add(userIdx);
        }
    }

    /**
     * mTime 시각에 mDelay 시간이 있는 mNewsID 뉴스가 mChannelID 뉴스 채널에 제공되고 mChannelID 뉴스 채널에 알림을 등록한 유저의 수를 반환
     *
     * mChannelID 뉴스 채널은 (mTime + mDelay) 시각에 뉴스 알림을 등록한 유저들에게 mNewsID 뉴스 알림을 보낸다.
     *
     * mChannelID 뉴스 채널에 알림 등록한 유저가 1명 이상 있음이 보장됨
     * mNewsID 는 중복으로 주어지지 않는다.
     *
     * @param mTime             : 현재 시각 (1 <= mTime <= 1,000,000,000)
     * @param mNewsID           : 뉴스의 ID (1 <= mNewsID <= 1,000,000,000)
     * @param mDelay            : 뉴스의 delay 시간 (1 <= mDelay <= 10,000)
     * @param mChannelID        : 뉴스를 제공받는 뉴스 채널의 ID (1 <= mChannelID <= 1,000,000,000)
     *
     * @return                  : mChannelID 뉴스 채널에 알림 등록한 유저의 수
     */
    int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID) {
        // newsID 인덱싱 처리
        int channelIdx = channelHM.get(mChannelID);
        int newsIdx = newsPoolLastIdx++;
        newsHM.put(mNewsID, newsIdx);

        // 뉴스 정보 생성
        newsPool[newsIdx].alarmTime = mTime + mDelay;
        newsPool[newsIdx].newsID = mNewsID;
        newsPool[newsIdx].channelID = channelIdx;
        newsPool[newsIdx].isValid = true;

        // 뉴스 PQ 에 정보 저장
        newsPQ.add(newsPool[newsIdx]);

        // 해당 채널의 유저 수 반환
        return channelsPool[channelIdx].userIdxList.size();
    }

    /**
     * mTime 시각에 mNewsID 뉴스가 취소되어 삭제된다.
     *
     * mNewsID 뉴스가 유저들에게 뉴스 알림이 보내지면 유저에게 있는 mNewsID 뉴스 알림도 삭제되어야 한다.
     *
     * mNewsID 뉴스는 offerNews() 에서 제공된 뉴스이다.
     * mNewsID 뉴스는 이미 취소되어 삭제된 뉴스일 수도 있다.
     *
     * @param mTime             : 현재 시각 (1 <= mTime <= 1,000,000,000)
     * @param mNewsID           : 뉴스의 ID (1 <= mNewsID <= 1,000,000,000)
     */
    void cancelNews(int mTime, int mNewsID) {
        // newsPool 에서 newsID로 조회하여 isValid 값을 false 로 처리한다.
        newsPool[newsHM.get(mNewsID)].isValid = false;
    }

    /**
     * mTime 시각에 mUID 유저가 받은 뉴스 알림의 개수를 반환하고, mUID 유저가 받은 뉴스의 ID를 최신 받은 순서대로 최대 3개 mRetIDs[] 에 저장한다. 삭제된 뉴스알림은 제외.
     *
     * mTime 시각에 유저에게 보내지는 뉴스 알림이 있는 경우 먼저 알림을 보낸 후, 유저가 받은 뉴스 알림의 개수를 반환한다.
     * 함수 호출 후, mUID 유저가 받은 뉴스 알림은 모두 삭제되어 알림 개수는 0 이 된다.
     *
     * mUID 유저가 같은 시간대에 2개 이상의 뉴스 알림을 받은 경우, 뉴스 ID 가 더 큰 경우 더 최신이다.
     * 받은 뉴스 알림이 2개 이하일 경우 받은 뉴스의 ID 만 최신 받은 순서대로 저장한다.
     * mUID 유저가 받은 뉴스 알림이 없을 경우 0을 반환한다.
     *
     * registerUser() 에 알림을 등록한 유저임이 보장된다.
     *
     * @param mTime             : 현재 시각 (1 <= mTime <= 1,000,000,000)
     * @param mUID              : 유저의 ID (1 <= mUID <= 1,000,000,000)
     * @param mRetIDs           : 받은 뉴스 ID 를 최신 순서대로 저장하는 공간
     *
     * @return                  : mUID 유저가 받은 뉴스 알림의 개수 (삭제된 뉴스 알림은 제외)
     */
    int checkUser(int mTime, int mUID, int[] mRetIDs) {
        // mTime 시간에 mUID 가 갖고 있는 뉴스 개수를 세야함. 그리고 그 최신 뉴스의 ID를 mRetIDs 에 최대 3개 저장해야한다.

        // mUID 로 userPool 에서 유저를 조회하고
        // 현재 그 유저가 갖고 있는 뉴스 개수를 센다.
        // 최신 뉴스를 뽑아 mRestIDs 에 3개를 저장한다.
        // 유저가 가진 알림은 모두 삭제된다.
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