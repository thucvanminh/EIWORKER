import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EIWORKER {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfStaff = sc.nextInt();
        int numHighestSalary = sc.nextInt();
        int moneyPerProduct = sc.nextInt();
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        HashMap<String, Double> outputList = new HashMap<String, Double>();
        for (int i = 0; i < numOfStaff; i++) {
            String name = sc.next();
            int numOfProduct = sc.nextInt();
            int khoangGiamTru = sc.nextInt();
            if (i <= 2) {
                outputList.put(name, calculateSalaryAfterTax(numOfProduct, moneyPerProduct, khoangGiamTru));
                sortedMap = outputList.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            } else {
                               
            }

        }
    }

    public static double calculateSalaryAfterTax(int numOfProduct, int moneyPerProduct, int khoangGiamTru) {
        int M = 1000000;
        int[] levels = { 0, 5 * M, 10 * M, 18 * M, 32 * M, 52 * M, 80 * M };
        double[] levelTax = { 0.95, 0.9, 0.85, 0.8, 0.75, 0.7, 0.65 };
        int calculteTax = numOfProduct * moneyPerProduct - khoangGiamTru - 9 * M;
        double outputNumber = 0;
        if (calculteTax <= 0) {
            return numOfProduct * moneyPerProduct;
        } else {
            for (int i = 0; i < levels.length; i++) {
                if (i < levelTax.length - 1 && calculteTax >= levels[i] && calculteTax >= levels[i + 1]) {
                    outputNumber += (levels[i + 1] - levels[i]) * levelTax[i];
                } else {
                    outputNumber += (calculteTax - levels[i]) * levelTax[i];
                }
            }
        }

        return outputNumber;

    }
}
