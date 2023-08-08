package SWEA.CodeBattle.실전실습.No_1_가게관리;

import java.util.*;

public class UserSolution_Test_Print {

    static HashMap<Integer, BuyInfo> buyInfoHM;         // key : bId, value : 구매 정보
    static HashMap<Integer, Integer> restInfoHM;        // key : mProduct, value : mProduct 남은 수량

    static HashMap<Integer, SellInfo> sellInfoHM;       // key : sId, value : 판매 정보

    static HashMap<Integer, PriorityQueue<BuyInfo>> priorityHM; // key : mProduct, value : mProduct 마다 가장 싼 가격, 구매 ID가 작은 순으로 나오도록 설정

    /**
     *  각 테스트 케이스 처음에 호출. 재고가 없는 상태가 된다.
     *  각 테스트 케이스에서 상품 종류는 600 이하이다.
     */
    public void init() {
        buyInfoHM = new HashMap<>();
        restInfoHM = new HashMap<>();
        sellInfoHM = new HashMap<>();
        priorityHM = new HashMap<>();
    }

    /**
     * 1. <구매>
     * - 호출 횟수 30,000 이하
     *
     * mProduct 상품을 mPrice 가격으로 mQuantity 개 구매. 구매 ID는 bID.
     * 구매 후에 가게가 보유 중인 mProduct 상품의 총 개수를 반환.
     *
     * @param bId           : 구매 ID (1 <= bId <= 1,000,000,000) 10억
     * @param mProduct      : 상품 번호 (1 <= mpProduct <= 1,000,000,000) 10억
     * @param mPrice        : 구매 가격 (1,000 <= mPrice <= 300,000) 30만
     * @param mQuantity     : 구매 수량 (10 <= mQuantity <= 100)
     *
     * @return              : mProduct 상품의 재고 수량을 반환.
     */
    public int buy(int bId, int mProduct, int mPrice, int mQuantity) {

//        System.out.print(" 1. buy : " + bId + ", " + mProduct + ", " + mPrice + ", " + mQuantity + "    ");
        BuyInfo buyInfo = new BuyInfo(bId, mProduct, mPrice, mQuantity, mQuantity);
        // bId 구매 정보를 기록
        buyInfoHM.put(bId, buyInfo);

        // mProduct 상품 수량을 기록
        restInfoHM.put(mProduct, restInfoHM.getOrDefault(mProduct, 0) + mQuantity);

        // 우선순위 저장
        if (!priorityHM.containsKey(mProduct)) {
            priorityHM.put(mProduct, new PriorityQueue<>());
            priorityHM.get(mProduct).add(buyInfo);
        } else {
            priorityHM.get(mProduct).add(buyInfo);
        }

//        System.out.println(restInfoHM.get(mProduct));
        return restInfoHM.get(mProduct);
    }

    /**
     * 2. <구매 취소>
     *
     * 구매 ID가 bId인 구매를 취소
     * bId로 구매했던 상품 수량이 모두 가게에 남아있을 경우에만 취소가 가능. 한 개라도 부족하다면 취소에 실패하고 -1 을 반환한다.
     * bId로 구매한 상품이 판매된적 있더라도 환불을 통해 다시 모두 재고가 되었다면 취소가 가능함을 유의하자.
     * bId로 구매한 내역이 없거나 이미 취소한 구매 ID 라면 취소에 실패하고 -1을 반환한다.
     *
     * 취소가 가능하다면 재고에서 bId로 구매했던 상품을 삭제하고 가게에 남아있는 동일 상품의 개수를 반환한다.
     *
     * @param bId           : 구매 ID (1 <= bId <= 1,000,000,000) 10억
     *
     * @return              : 취소 성공 -> 취소 상품과 동일한 상품이 가게에 남아있는 개수 반환
     *                      : 취소 실패 -> -1 반환
     */
    public int cancel(int bId) {
        // 취소할 구매 정보
//        System.out.print(" 2. cancel : " + bId + "    ");

        // 만약 구매한 내역이 없거나
        // 구매 내역의 구매 수량(불변)과 해당 구매 상품의 남은 수량이 한개라도 부족하거나
        // 이미 취소한 구매 ID 라면 -1 반환
        if (buyInfoHM.get(bId) == null || buyInfoHM.get(bId).mQuantity > buyInfoHM.get(bId).mRest || buyInfoHM.get(bId).mRest == 0) {
//            System.out.println(-1);
            return -1;
        }

        // bId 로 구매한 상품 삭제 - 남은 수량 0 처리
        restInfoHM.put(buyInfoHM.get(bId).mProduct, restInfoHM.get(buyInfoHM.get(bId).mProduct) - buyInfoHM.get(bId).mRest);
        buyInfoHM.get(bId).mRest = 0;

        // 반환할 구매 ID 에서 남은 수량을 반환한다.
//        System.out.println(restInfoHM.get(buyInfoHM.get(bId).mProduct));
        return restInfoHM.get(buyInfoHM.get(bId).mProduct);
    }

    /**
     * 3. <판매>
     * - 호출 횟수 30,000 이하
     *
     * mProduct 상품을 mPrice 가격으로 mQuantity 개 판매. 판매 ID 는 sId.
     * 판매 ID, 구매 ID는 서로 독립적. 따라서 값이 서로 동일한 경우도 있다.
     * mProduct 상품의 재고 수량이 mQuantity 보다 작다면, 판매에 실패하고 -1을 반환
     *
     * 판매가 가능하면 가장 싸게 구매한 상품부터 판매.
     * 가격이 동일하면 구매 ID 값이 작은 상품부터 판매
     * 판매 후에 총 판매 수익 반환. 개당 판매 수익은 판매 가격에서 구매 가격을 뺀 값.
     *
     * @param sId           : 판매 ID (1 <= sId <= 1,000,000,000) 10억
     * @param mProduct      : 상품 번호 (1 <= mpProduct <= 1,000,000,000) 10억
     * @param mPrice        : 판매 가격 (2,000 <= mPrice <= 300,000) 30만
     * @param mQuantity     : 판매 수량 (10 <= mQuantity <= 100)
     *
     * @return              : 판매 성공 -> sId 판매로 발생한 총 수익 반환
     *                      : 판매 실패 -> -1 반환
     */
    public int sell(int sId, int mProduct, int mPrice, int mQuantity) {

//        System.out.print(" 3. sell : " + sId + ", " + mProduct + ", " + mPrice + ", " + mQuantity + "    ");

//        System.out.println("  " + mProduct + " 번 상품 재고량 : " + restInfoHM.get(mProduct));
//        System.out.println("  mQuantity : " + mQuantity);
        // mProduct 상품의 재고 수량이 mQuantity 보다 작으면 return -1
        if (restInfoHM.get(mProduct) < mQuantity) {
//            System.out.println(-1);
            return -1;
        }

        // 전체 상품 개수 업데이트
        restInfoHM.put(mProduct, restInfoHM.get(mProduct) - mQuantity);

        // 판매 내역 기록
        sellInfoHM.put(sId, new SellInfo(mProduct, false));

        // 가장 싼것, 같다면 구매 ID 값이 작은 것부터 판매
        int income = 0;

        while (mQuantity > 0) {
            BuyInfo buyInfo = priorityHM.get(mProduct).peek();

            int bid = buyInfo.bId;
            int cnt = Math.min(buyInfoHM.get(bid).mRest, mQuantity);

            buyInfoHM.get(bid).mRest -= cnt;
            mQuantity -= cnt;

            income += (mPrice - buyInfoHM.get(bid).mPrice) * cnt;
            sellInfoHM.get(sId).historyList.add(new sHistory(bid, cnt));

            if (buyInfoHM.get(bid).mRest <= 0) priorityHM.get(mProduct).poll();
        }


        // 판매 수익을 반환. (판매 가격에서 구매 가격을 뺀 값)
//        System.out.println("  " + income);
        return income;
    }

    /**
     * 4. <판매 취소>
     *
     * sId로 판매한 상품에 대해 환불.
     * 환불해준 상품의 총 개수 반환
     * 환불 상품은 모두 재고로 쌓인다.
     *
     * sId로 판매한 내역이 없거나 이미 환블해준 판매 ID 라면 환불에 실패하고 -1 반환
     *
     * @param sId           : 판매 ID (1 <= sId <= 1,000,000,000) 10억
     *
     * @return              : 환불 성공 -> 환불 상품 총 개수 반환
     *                      : 환불 실패 -> -1 반환
     */
    public int refund(int sId) {

//        System.out.print(" 4. refund : " + sId + "    ");
        // sId 로 판매한 내역이 없거나 이미 환불해준 거라면 return -1
        if (sellInfoHM.get(sId) == null || sellInfoHM.get(sId).isRefund) {
//            System.out.println(-1);
            return -1;
        }

        // sId 로 판매한 상품에 대해 환불
        int totalCnt = 0;
        int product = sellInfoHM.get(sId).mProduct;
        sellInfoHM.get(sId).isRefund = true;
//        System.out.println("  환불할 상품 번호 : " + product);

        for (int i = 0; i < sellInfoHM.get(sId).historyList.size(); i++) {
            sHistory sHistory = sellInfoHM.get(sId).historyList.get(i);
            int bid = sHistory.bid;
//            System.out.println("  환불할 구매 ID : " + bid);
            int cnt = sHistory.cnt;

            if (buyInfoHM.get(bid).mRest == 0) {
                priorityHM.get(product).add(new BuyInfo(bid, product, buyInfoHM.get(bid).mPrice, buyInfoHM.get(bid).mQuantity, cnt));
            }

            buyInfoHM.get(bid).mRest += cnt;
            totalCnt += cnt;
        }

        restInfoHM.put(product, restInfoHM.getOrDefault(product, 0) + totalCnt);

//        System.out.println(totalCnt);
        return totalCnt;
    }

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
        int bid, cnt;

        public sHistory(int bid, int cnt) {
            this.bid = bid;
            this.cnt = cnt;
        }
    }
}
