package infosys_prep;

import java.util.*;

public class JobSequencing {
    static class Job {
        int id;
        int deadline;
        int profit;
    }

    public static boolean comparison(Job a, Job b) {
        return a.profit > b.profit;
    }

    public static int[] JobScheduling(Job[] arr, int n) {

        // Sort the jobs by profit in descending order
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);

        // Find the maximum deadline among all jobs
        int maxDL = arr[0].deadline;
        for(int i = 1; i < n; i++) {
            maxDL = Math.max(maxDL, arr[i].deadline);
        }

        // Create an array to store the slots for the jobs
        int[] slots = new int[maxDL + 1];
        // Initialize all slots as unoccupied
        Arrays.fill(slots, -1);

        int count = 0, jobProfit = 0;

        // Try to assign jobs to the slots
        for(int i = 0; i < n; i++) {

            // find a slot for the current job, starting from the job's deadline
            for(int j = arr[i].deadline; j >= 0; j--) {
                // if the slot is available, assign it
                if(slots[j] == -1) {
                    // Assign
                    slots[j] = i;
                    // increment job count
                    count++;
                    // add the profit of the job
                    jobProfit += arr[i].profit;
                    break;
                }
            }
        }
        return new int[]{count, jobProfit};
    }

    public static void main(String[] args) {
        int n = 8; // No. of jobs

        Job[] arr = new Job[] {
                new Job() {{id = 6; deadline = 2; profit = 80;}},
                new Job() {{id = 3; deadline = 6; profit = 70;}},
                new Job() {{id = 4; deadline = 6; profit = 65;}},
                new Job() {{id = 2; deadline = 5; profit = 60;}},
                new Job() {{id = 5; deadline = 4; profit = 25;}},
                new Job() {{id = 8; deadline = 2; profit = 22;}},
                new Job() {{id = 1; deadline = 4; profit = 20;}},
                new Job() {{id = 7; deadline = 2; profit = 10;}}
        };

        // Call the JobScheduling function
        int[] ans = JobScheduling(arr, n);

        // Output the result
        System.out.println(ans[0] + " " + ans[1]);

    }
}
