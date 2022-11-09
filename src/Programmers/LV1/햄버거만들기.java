package Programmers.LV1;

import java.util.ArrayList;

public class 햄버거만들기 {
    public static void main(String[] args) {
        int[] ingredient = new int[]{2,1,1,2,3,1,2,3,1};

        System.out.println(solution(ingredient));
    }

    public static int solution(int[] ingredient) {
        int answer = 0;

        ArrayList<Integer> ingredientList = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            result.add(i);
        }
        result.add(1);

        for (int i = 0; i < ingredient.length; i++) {
            ingredientList.add(ingredient[i]);

            if (ingredientList.size() >= 4) {
                ArrayList<Integer> tempList = new ArrayList<>();
                for (int j = 4; j > 0; j--) {
                    tempList.add(ingredientList.get(ingredientList.size() - j));
                }

                if (tempList.equals(result)) {
                    answer++;

                    int v = 4;
                    while (v-- > 0) {
                        ingredientList.remove(ingredientList.size() - 1);
                    }
                }
            }
        }

        return answer;
    }
}
