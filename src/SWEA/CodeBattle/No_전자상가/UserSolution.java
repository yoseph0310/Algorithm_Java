package SWEA.CodeBattle.No_전자상가;

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

    static int CHARGE;

    /**
     * 각 TC에 처음에 호출됨. 창고간 운송료를 알려줌.
     * 기존 목록은 초기화 됨
     *
     * @param mCharge           창고 간 운송료 (1 <= mCharge <= 100,000)
     */
    void init(int mCharge) {
        System.out.println("0. CMD_INIT - 주문 시작. 창고 간 운송료 입력");
        System.out.println("  mCharge : " + mCharge);

        // 2. order 에서 사용할 CHARGE 입력
        CHARGE = mCharge;

        // 1. stock 에서 부품 정보가 추가되면 저장할 자료구조 초기화


    }

    /**
     * << 호출 횟수 : 4,000 이하 >>
     *
     * 부품 목록에 새로운 부품을 추가.
     * 중복된 정보는 들어오지 않는다. (종류와 가격 성능이 모두 같은 입력이 없다는 뜻)
     *
     * @param mType             부품 종류 (0 <= mType <= 2)
     * @param mPrice            부품 가격 (1 <= mPrice <= 100,000)
     * @param mPerformance      부품 성능 (1 <= mPerformance <= 1,0000,000)
     * @param mPosition         부품이 있는 창고 번호 (0 <= mPosition <= 1)
     *
     * @return                  입고된 창고에 동일한 type 인 부품의 가짓수를 반환
     */
    int stock(int mType, int mPrice, int mPerformance, int mPosition) {
        System.out.println("1. CMD_STOCK - 새로운 부품 추가");
        System.out.println("  mType || " + mType + " || mPrice || " + mPrice + " || mPerformance || " + mPerformance + " || mPosition || " + mPosition);


        return 0;
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
        System.out.println("2. CMD_ORDER - 소비자 예산 안에서 최대 성능 부품 조합 선택");
        System.out.println("  mBudget : " + mBudget);

        Solution.Result res = new Solution.Result();

        return res;
    }

    static class Product {
        int type, price, performance, position;

        public Product(int type, int price, int performance, int position) {
            this.type = type;
            this.price = price;
            this.performance = performance;
            this.position = position;
        }


    }
}
