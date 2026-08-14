package algo101.ProbabilisticAlgo;

import java.util.Random;

public class ProbabilisticCounting {

    // Pre-computed Fibonacci numbers F_0 to F_15
    private static final int[] FIB = {
            0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610
    };

    // 4-bit register storing values from 0 to 15
    private byte counter = 0;
    private final Random random = new Random();

    /**
     * Increment step using the probability formula:
     * P(c) = 1 / (F_{c+1} - F_c) = 1 / F_{c-1}
     */
    public void add() {
        // Cap at 4-bit maximum (15)
        if (counter >= 15) {
            return;
        }

        double probability;

        // Base cases for small values where denominator differences are <= 1
        if (counter < 3) {
            probability = 1.0;
        } else {
            // Gap formula: difference between next estimate and current estimate
            int stepGap = FIB[counter + 1] - FIB[counter]; // Equals FIB[counter - 1]
            probability = 1.0 / stepGap;
        }

        // Increment register with probability P(c)
        if (random.nextDouble() < probability) {
            counter++;
        }
    }

    /**
     * @return Current estimated count (F_c)
     */
    public int estimate() {
        return FIB[counter];
    }

    /**
     * @return Raw 4-bit register value (0 to 15)
     */
    public byte getRawCounter() {
        return counter;
    }

    // --- Simulation & Verification Harness ---
    public static void main(String[] args) {
        int[] targetCounts = {5, 20, 50, 100, 300, 600};
        int numTrials = 10_000;

        System.out.printf("%-12s | %-14s | %-18s%n", "True Count", "Avg Estimate", "Raw Register (4-bit)");
        System.out.println("-------------------------------------------------------");

        for (int trueCount : targetCounts) {
            long totalEstimate = 0;
            long totalRegister = 0;

            for (int i = 0; i < numTrials; i++) {
                ProbabilisticCounting counter = new ProbabilisticCounting();
                for (int j = 0; j < trueCount; j++) {
                    counter.add();
                }
                totalEstimate += counter.estimate();
                totalRegister += counter.getRawCounter();
            }

            double avgEstimate = (double) totalEstimate / numTrials;
            double avgRegister = (double) totalRegister / numTrials;

            System.out.printf("%-12d | %-14.2f | %-18.2f%n", trueCount, avgEstimate, avgRegister);
        }
    }
}
