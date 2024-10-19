import java.util.*;
import java.math.BigInteger;

public class ShamirSecretSharing {

    // Function to convert a value from a given base to decimal
    public static BigInteger decodeValue(String value, int base) {
        return new BigInteger(value, base);
    }

    // Lagrange interpolation to compute the polynomial constant term
    public static double lagrangeInterpolation(List<Point> points) {
        double constant = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            double term = points.get(i).y.doubleValue();  // yi

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term *= (0.0 - points.get(j).x) / (points.get(i).x - points.get(j).x);
                }
            }

            constant += term;
        }

        return constant;
    }

    // Function to parse the input
    public static List<Point> parseInput(Map<Integer, BaseValue> inputMap) {
        List<Point> points = new ArrayList<>();

        for (Map.Entry<Integer, BaseValue> entry : inputMap.entrySet()) {
            int x = entry.getKey();
            int base = entry.getValue().base;
            String encodedValue = entry.getValue().value;
            BigInteger y = decodeValue(encodedValue, base);
            points.add(new Point(x, y));
        }

        return points;
    }

    // Point class to store the (x, y) coordinates
    static class Point {
        int x;
        BigInteger y;

        public Point(int x, BigInteger y) {
            this.x = x;
            this.y = y;
        }
    }

    // BaseValue class to store the base and value from the input
    static class BaseValue {
        int base;
        String value;

        public BaseValue(int base, String value) {
            this.base = base;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        Map<Integer, BaseValue> testCase1 = new HashMap<>();
        testCase1.put(1, new BaseValue(10, "4"));
        testCase1.put(2, new BaseValue(2, "111"));
        testCase1.put(3, new BaseValue(10, "12"));
        testCase1.put(6, new BaseValue(4, "213"));

        // Test Case 2
        Map<Integer, BaseValue> testCase2 = new HashMap<>();
        testCase2.put(1, new BaseValue(6, "13444211440455345511"));
        testCase2.put(2, new BaseValue(15, "aed7015a346d63"));
        testCase2.put(3, new BaseValue(15, "6aeeb69631c227c"));
        testCase2.put(4, new BaseValue(16, "e1b5e05623d881f"));
        testCase2.put(5, new BaseValue(8, "316034514573652620673"));
        testCase2.put(6, new BaseValue(3, "2122212201122002221120200210011020220200"));
        testCase2.put(7, new BaseValue(3, "20120221122211000100210021102001201112121"));
        testCase2.put(8, new BaseValue(6, "20220554335330240002224253"));
        testCase2.put(9, new BaseValue(12, "45153788322a1255483"));
        testCase2.put(10, new BaseValue(7, "1101613130313526312514143"));

        // Solve for Test Case 1
        List<Point> points1 = parseInput(testCase1);
        double secret1 = lagrangeInterpolation(points1);
        System.out.println("The secret (constant term) for Test Case 1 is: " + Math.round(secret1));

        // Solve for Test Case 2
        List<Point> points2 = parseInput(testCase2);
        double secret2 = lagrangeInterpolation(points2);
        System.out.println("The secret (constant term) for Test Case 2 is: " + Math.round(secret2));
    }
}
