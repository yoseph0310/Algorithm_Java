package Z_Algorithm.RabinKarp;

public class RabinKarp {
    public static void main(String[] args) throws Exception {
        String parent = "ababacabacaabacaaba";
        String pattern = "abacaaba";

        findStringPattern(parent, pattern);
    }

    static void findStringPattern(String parent, String pattern) {
        final int MOD = 100_000_007;
        int parentSize = parent.length();
        int patternSize = pattern.length();

        long parentHash = 0, patternHash = 0, power = 1;

        for (int i = 0; i <= parentSize - patternSize; i++) {
            if (i == 0) {
                for (int j = 0; j < patternSize; j++) {
                    parentHash = (parentHash + (parent.charAt(patternSize - 1 - j)) * power) % MOD;
                    patternHash = (patternHash + (pattern.charAt(patternSize - 1 - j)) * power) % MOD;

                    if (j < patternSize - 1) {
                        power = (power % MOD * 31) % MOD;
                    }
                }
            } else {
                parentHash = 31 * parentHash % MOD - 31 * parent.charAt(i - 1) * power % MOD + parent.charAt(i + patternSize - 1);
                parentHash %= MOD;
            }

            System.out.println(i + " >> 해시값 비교 : " + parentHash + ", " + patternHash);

            if (parentHash == patternHash) {
                System.out.println(i + " 번째에서 같은 문자열");
            }
        }
    }
}
