package SWEA.CodeBattle.No_전자상가;

import java.util.ArrayList;
import java.util.List;

public class UserSolution_2 {

    static int CHARGE;
    static WareHouse[][] wareHouses;

    static final int MAX_PERFORMANCE = 1_000_001;
    static final int MAX_PRICE = 300_001;
    static final int INF = Integer.MAX_VALUE;

    void init(int mCharge) {
        wareHouses = new WareHouse[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                wareHouses[i][j] = new WareHouse();
            }
        }

        CHARGE = mCharge;
    }

    int stock(int mType, int mPrice, int mPerformance, int mPosition) {
        wareHouses[mPosition][mType].list.add(new Product(mPrice, mPerformance));
        return wareHouses[mPosition][mType].list.size();
    }

    Solution.Result order(int mBudget) {
        int resPrice = 0;

        int L = 0;
        int H = MAX_PERFORMANCE;

        while (L < H - 1) {
            int mid = (L + H) / 2;

            int[][] minWH = new int[2][3];

            // maximum : 4,000 회
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    int minPrice = MAX_PRICE;
                    List<Product> curList = wareHouses[i][j].list;

                    for (Product product : curList) {
                        int curPrice = product.price;
                        int curPerformance = product.performance;

                        if (curPrice <= minPrice && curPerformance >= mid) {
                            minPrice = curPrice;
                        }
                    }

                    minWH[i][j] = minPrice;
                }
            }

            int min = INF;
            for (int bit = 0; bit < 8; bit++) {
                int price = (bit == 0 || bit == 7) ? 0 : CHARGE;

                for (int i = 0; i < 3; i++) {
                    price += minWH[(bit >> i) & 1][i];
                }

                min = Math.min(min, price);
            }

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
