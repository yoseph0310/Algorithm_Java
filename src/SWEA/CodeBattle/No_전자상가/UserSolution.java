package SWEA.CodeBattle.No_전자상가;

import java.util.ArrayList;
import java.util.List;

/**
 *  - 컴퓨터 조립시에는 3종류의 부품을 각각 하나씩 선택.
 *  - 그 중 가장 성능이 낮은 부품의 성능이 컴퓨터 성능이 된다.
 *  - 만약 최대 성능을 내는 조합이 여러 개면 가장 낮은 가격이 낮은 조합으로 선택
 *
 *  - 한 창고의 부품으로만 선택하면 그곳에서 바로 출고.
 *  - 두 창고의 부품이 모두 필요하면 운송료를 지불하고 부품을 모은 후 출고.
 *
 *  - 소비자 지불 가격 : 부품 가격 + 운송료
 */
public class UserSolution {

    static WareHouse[][] wareHouses;
    static int CHARGE;

    static final int MAX_PERFORMANCE = 1_000_001;
    static final int MAX_PRICE = 300_001;
    static final int INF = Integer.MAX_VALUE;
    /**
     * 각 TC에 처음에 호출됨. 창고간 운송료를 알려줌.
     * 기존 목록은 초기화 됨
     *
     * @param mCharge           창고 간 운송료 (1 <= mCharge <= 100,000)
     */
    void init(int mCharge) {
        wareHouses = new WareHouse[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                wareHouses[i][j] = new WareHouse();
            }
        }

        CHARGE = mCharge;
    }

    /**
     * << 호출 횟수 : 4,000 이하 >>
     *
     * 부품 목록에 새로운 부품을 추가.
     * 중복된 정보는 들어오지 않는다. (종류와 가격 성능이 모두 같은 입력이 없다는 뜻)
     *
     * @param mType             부품 종류 (0 <= mType <= 2)
     * @param mPrice            부품 가격 (1 <= mPrice <= 100,000)
     * @param mPerformance      부품 성능 (1 <= mPerformance <= 1,000,000)
     * @param mPosition         부품이 있는 창고 번호 (0 <= mPosition <= 1)
     *
     * @return                  입고된 창고에 동일한 type 인 부품의 가짓수를 반환
     */
    int stock(int mType, int mPrice, int mPerformance, int mPosition) {
        wareHouses[mPosition][mType].list.add(new Product(mPrice, mPerformance));
        return wareHouses[mPosition][mType].list.size();
    }

    /**
     * << 호출 횟수 : 400 이하 >>
     *
     * 소비자의 예산이 mBudget 일 때 예산 안에서 최대의 성능을 내는 부품 조합을 선택.
     * 같은 성능이면 가격이 가장 낮은 조합을 선택
     * 부품이 출고되면 즉각 채워 넣으므로 품절되지 않는다. ( remove 는 없다. )
     *
     * @param mBudget           소비자 예산 (1 <= mBudget <= 500,000)
     *
     * @return                  주어진 예산으로 조립이 가능한 경우 : Result 구조체의 mPerformance 에 컴퓨터 성능, mPrice 에 캄퓨터 가격을 반환
     *                          주어진 예산으로 조립이 불가능한 경우 : mPerformance, mPrice 에 모두 0 반환
     */
    Solution.Result order(int mBudget) {
        int resPrice = 0;

        int L = 0;
        int H = MAX_PERFORMANCE;

        while (L < H - 1) {
            int mid = (L + H) / 2;

            int[][] minWH = new int[2][3];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    int minPrice = MAX_PRICE;
                    List<Product> curList = wareHouses[i][j].list;

                    for (int k = 0; k < curList.size(); k++) {
                        int curPrice = curList.get(k).price;
                        int curPerformance = curList.get(k).performance;

                        if (curPrice <= minPrice && curPerformance >= mid) {
                            minPrice = curPrice;
                        }
                    }

                    minWH[i][j] = minPrice;
                }
            }

            int sumWare1 = 0;
            int sumWare2 = 0;
            int sumWare1to2 = 0;

            for (int i = 0; i < 3; i++) {
                sumWare1 += minWH[0][i];
                sumWare2 += minWH[1][i];

                sumWare1to2 += Math.min(minWH[0][i], minWH[1][i]);
            }

            int min = Math.min(sumWare1, Math.min(sumWare2, sumWare1to2 + CHARGE));

            if (min <= mBudget) {
                resPrice = min;
                L = mid;
            } else {
                H = mid;
            }
        }

        Solution.Result result = new Solution.Result();
        result.mPrice = resPrice;
        result.mPerformance = resPrice == 0 ? 0 : L;

        return result;
    }

    static class Product {
        int price, performance;

        public Product(int price, int performance) {
            this.price = price;
            this.performance = performance;
        }
    }

    static class WareHouse {
        List<Product> list = new ArrayList<>();
    }
}
/*
 *  1. 부품 선택 최선
 *
 *  - 성능이 좋기만 하다면 가격이 낮은 것이 무조건 유리.
 *  - 성능이 같다면 가격이 낮은 것이 유리
 *
 *  -> 성능이 좋은 것중 가격이 가장 낮은 부품을 선택해 나가는 것이 최선
 *
 *  2. 최대의 성능값도 찾아야함.
 *
 *  최소 성능 n 이 주어졌을 때 최소비요은 그리디 방법으로 빨리 찾을 수 잇음
 *
 *  3. 최소 성능 n 이 주어졌을 때 최소 비용을 빠르게 찾는법
 *
 *  - 이진탐색, 이진트리
 *  - 균형 이진 트리
 */
