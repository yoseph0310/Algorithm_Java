package SWEA.CodeBattle.실전실습.No_1_가게관리.재풀이;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 *  - 상품 종류 600 이하
 *  - buy 호출 횟수 30,000 이하
 *  - sell 호출 횟수 30,000 이하
 *  - 모든 함수 호출 횟수 총합 80,000 이하
 */
public class UserSolution {

    static class BuyInfo implements Comparable<BuyInfo> {
        int bId, mProduct, mPrice, mQuantity, mRest;

        public BuyInfo(int bId, int mProduct, int mPrice, int mQuantity, int mRest) {
            this.bId = bId;
            this.mProduct = mProduct;
            this.mPrice = mPrice;
            this.mQuantity = mQuantity;
            this.mRest = mRest;
        }

        @Override
        public int compareTo(BuyInfo b) {
            if (b.mPrice == this.mPrice) return this.bId - b.bId;
            return this.mPrice - b.mPrice;
        }
    }

    static class SellInfo {
        int mProduct;
        boolean isRefund;
        List<sHistory> historyList;

        public SellInfo(int mProduct, boolean isRefund) {
            this.mProduct = mProduct;
            this.isRefund = isRefund;
            this.historyList = new ArrayList<>();
        }
    }

    static class sHistory {
        int bId, cnt;

        public sHistory(int bId, int cnt) {
            this.bId = bId;
            this.cnt = cnt;
        }
    }

    static HashMap<Integer, BuyInfo> buyInfoHM;
    static HashMap<Integer, Integer> restInfoHM;

    static HashMap<Integer, SellInfo> sellInfoHM;
    static HashMap<Integer, PriorityQueue<BuyInfo>> priorityHM;

    void init() {
        buyInfoHM = new HashMap<>();
        restInfoHM = new HashMap<>();

        sellInfoHM = new HashMap<>();
        priorityHM = new HashMap<>();
    }

    int buy(int bId, int mProduct, int mPrice, int mQuantity) {
        BuyInfo buyInfo = new BuyInfo(bId, mProduct, mPrice, mQuantity, mQuantity);
        buyInfoHM.put(bId, buyInfo);

        restInfoHM.put(mProduct, restInfoHM.getOrDefault(mProduct, 0) + mQuantity);

        if (!priorityHM.containsKey(mProduct)) {
            priorityHM.put(mProduct, new PriorityQueue<>());
            priorityHM.get(mProduct).add(buyInfo);
        } else {
            priorityHM.get(mProduct).add(buyInfo);
        }

        return restInfoHM.get(mProduct);
    }

    int cancel(int bId) {
        if (buyInfoHM.get(bId) == null || buyInfoHM.get(bId).mQuantity > buyInfoHM.get(bId).mRest
                || buyInfoHM.get(bId).mRest == 0) return -1;

        restInfoHM.put(buyInfoHM.get(bId).mProduct, restInfoHM.get(buyInfoHM.get(bId).mProduct) - buyInfoHM.get(bId).mRest);
        buyInfoHM.get(bId).mRest = 0;

        return restInfoHM.get(buyInfoHM.get(bId).mProduct);
    }

    int sell(int sId, int mProduct, int mPrice, int mQuantity) {
        if (restInfoHM.get(mProduct) < mQuantity) return -1;

        restInfoHM.put(mProduct, restInfoHM.get(mProduct) - mQuantity);
        sellInfoHM.put(sId, new SellInfo(mProduct, false));

        int income = 0;

        while (mQuantity > 0) {
            BuyInfo buyInfo = priorityHM.get(mProduct).peek();

            int bId = buyInfo.bId;
            int cnt = Math.min(buyInfoHM.get(bId).mRest, mQuantity);

            buyInfoHM.get(bId).mRest -= cnt;
            mQuantity -= cnt;

            income += (mPrice - buyInfoHM.get(bId).mPrice) * cnt;
            sellInfoHM.get(sId).historyList.add(new sHistory(bId, cnt));

            if (buyInfoHM.get(bId).mRest <= 0) priorityHM.get(mProduct).poll();
        }

        return income;
    }

    int refund(int sId) {
        if (sellInfoHM.get(sId) == null || sellInfoHM.get(sId).isRefund) return -1;

        int totalCnt = 0;
        int product = sellInfoHM.get(sId).mProduct;
        sellInfoHM.get(sId).isRefund = true;

        for (int i = 0; i < sellInfoHM.get(sId).historyList.size(); i++) {
            sHistory sHistory = sellInfoHM.get(sId).historyList.get(i);
            int bid = sHistory.bId;
            int cnt = sHistory.cnt;

            if (buyInfoHM.get(bid).mRest == 0) {
                priorityHM.get(product).add(new BuyInfo(bid, product, buyInfoHM.get(bid).mPrice, buyInfoHM.get(bid).mQuantity, cnt));
            }

            buyInfoHM.get(bid).mRest += cnt;
            totalCnt += cnt;
        }

        restInfoHM.put(product, restInfoHM.getOrDefault(product, 0) + totalCnt);

        return totalCnt;
    }

}
