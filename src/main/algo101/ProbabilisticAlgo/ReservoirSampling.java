package algo101.ProbabilisticAlgo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReservoirSampling {

    private static final Random random = new Random();

    // Custom class to return both the chosen person and the number of updates made
    static class Result {
        String chosenPerson;
        int updateCount;

        Result(String chosenPerson, int updateCount) {
            this.chosenPerson = chosenPerson;
            this.updateCount = updateCount;
        }
    }

    /**
     * Selects one person uniformly at random and counts how many times
     * the choice was updated.
     */
    public static Result pickRandomPerson(List<String> stream) {
        if (stream == null || stream.isEmpty()) {
            return new Result(null, 0);
        }

        String chosenPerson = null;
        int count = 0;        // Tracks step k
        int updateCount = 0;  // Tracks number of choice changes

        for (String person : stream) {
            count++;

            // Step k: Update choice with probability 1/k
            if (random.nextInt(count) == 0) {
                chosenPerson = person;
                updateCount++; // Increment whenever a new person is chosen
            }
        }

        return new Result(chosenPerson, updateCount);
    }

    public static void main(String[] args) {
        List<String> stream = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");

        int runs = 60_000;
        int[] selectionCounts = new int[stream.size()];
        long totalUpdatesAcrossAllRuns = 0; // Tracks sum of changes across all runs

        for (int i = 0; i < runs; i++) {
            Result result = pickRandomPerson(stream);

            // 1. Record selected person
            int index = stream.indexOf(result.chosenPerson);
            selectionCounts[index]++;

            // 2. Accumulate choice changes
            totalUpdatesAcrossAllRuns += result.updateCount;
        }

        // --- Print Selection Uniformity ---
        System.out.println("--- 1. Selection Uniformity (" + runs + " runs) ---");
        for (int i = 0; i < stream.size(); i++) {
            double percentage = (selectionCounts[i] * 100.0) / runs;
            System.out.printf("%s selected: %d times (%.2f%%)\n", stream.get(i), selectionCounts[i], percentage);
        }

        // --- Print Average Choice Changes ---
        System.out.println("\n--- 2. Average Number of Choice Changes ---");
        double averageUpdates = (double) totalUpdatesAcrossAllRuns / runs;
        System.out.printf("Total choice updates across %d runs: %d\n", runs, totalUpdatesAcrossAllRuns);
        System.out.printf("Simulated Average Updates per Run: %.4f\n", averageUpdates);

        // Calculate theoretical expected value H_n = 1/1 + 1/2 + 1/3 + 1/4 + 1/5 + 1/6
        double theoreticalExpected = 0.0;
        for (int k = 1; k <= stream.size(); k++) {
            theoreticalExpected += 1.0 / k;
        }
        System.out.printf("Theoretical Expected Updates (H_6): %.4f\n", theoreticalExpected);
    }
}