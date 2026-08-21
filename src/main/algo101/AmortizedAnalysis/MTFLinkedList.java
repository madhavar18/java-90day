package algo101.AmortizedAnalysis;

import java.util.Random;

public class MTFLinkedList {

    static class Node {
        int id;
        Node next;

        Node(int id) {
            this.id = id;
        }
    }

    static class LinkedList {
        Node head;

        void insert(int id) {
            Node node = new Node(id);
            node.next = head;
            head = node;
        }

        // Standard linear search
        long find(int key) {
            long comparisons = 0;
            Node curr = head;
            while (curr != null) {
                comparisons++;
                if (curr.id == key) break;
                curr = curr.next;
            }
            return comparisons;
        }

        // Move-To-Front linear search
        long find_mtf(int key) {
            long comparisons = 0;
            Node curr = head;
            Node prev = null;

            while (curr != null) {
                comparisons++;
                if (curr.id == key) {
                    // Move target node to head if it isn't already there
                    if (prev != null) {
                        prev.next = curr.next;
                        curr.next = head;
                        head = curr;
                    }
                    break;
                }
                prev = curr;
                curr = curr.next;
            }
            return comparisons;
        }

        public static int getSkewedQueries(int listSize, Random rand) {
            if (rand.nextDouble() < 0.80) {
                // Hot items are at the tail end of the initial list
                return 801 + rand.nextInt(200);
            } else {
                return 1 + rand.nextInt(800);
            }
        }


        public static void main(String[] args) {
            int listSize = 1000;
            int numQueries = 100000;

            // Build single base list [1..1000]
            LinkedList list = new LinkedList();
            for (int i = listSize; i >= 1; i--) {
                list.insert(i);
            }

            // Generate 100,000 queries using simple 80/20 rule
            Random rand = new Random(42);
            int[] accessLog = new int[numQueries];
            for (int i = 0; i < numQueries; i++) {
                accessLog[i] = getSkewedQueries(listSize, rand);
            }

            // 1. Run Standard Find (List structure stays unchanged)
            long stdComparisons = 0;
            long startTime = System.nanoTime();
            for (int key : accessLog) {
                stdComparisons += list.find(key);
            }
            long stdDuration = System.nanoTime() - startTime;

            // 2. Run Move-To-Front Find directly on the same list
            long mtfComparisons = 0;
            startTime = System.nanoTime();
            for (int key : accessLog) {
                mtfComparisons += list.find_mtf(key);
            }
            long mtfDuration = System.nanoTime() - startTime;

            // Benchmark Results
            printResults(numQueries, stdComparisons, mtfComparisons, stdDuration, mtfDuration);
        }

        private static void printResults(int numQueries, long stdComp, long mtfComp, long stdTime, long mtfTime) {
            System.out.println("================ BENCHMARK RESULTS ================");
            System.out.printf("Total Queries Executed:          %,d\n", numQueries);
            System.out.println("---------------------------------------------------");
            System.out.printf("Standard Search Comparisons:     %,d\n", stdComp);
            System.out.printf("Move-To-Front Comparisons:       %,d\n", mtfComp);
            System.out.printf("Comparison Reduction:            %.2f%%\n",
                    100.0 * (stdComp - mtfComp) / stdComp);
            System.out.println("---------------------------------------------------");
            System.out.printf("Avg Comparisons per Search (STD): %.2f\n", (double) stdComp / numQueries);
            System.out.printf("Avg Comparisons per Search (MTF): %.2f\n", (double) mtfComp / numQueries);
            System.out.println("---------------------------------------------------");
            System.out.printf("Standard Execution Time:         %.2f ms\n", stdTime / 1e6);
            System.out.printf("MTF Execution Time:              %.2f ms\n", mtfTime / 1e6);
            System.out.printf("Execution Speedup:               %.2fx faster\n", (double) stdTime / mtfTime);
            System.out.println("===================================================");
        }
    }
}