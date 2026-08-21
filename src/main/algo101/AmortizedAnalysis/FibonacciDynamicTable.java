package algo101.AmortizedAnalysis;

import java.util.Arrays;

public class FibonacciDynamicTable<T> {
    private Object[] table;
    private int[] credits;      // Tracks coins stacked on each active slot
    private int shrinkBank;     // Bank reserve collected specifically from pop operations
    private int size;           // Current number of elements (n)
    private int capacity;       // Current capacity (F_k)
    private int fPrev1;         // F_{k-1}
    private int fPrev2;         // F_{k-2}

    public FibonacciDynamicTable() {
        // Initial state: F_2 = 1, F_3 = 2, F_4 = 3
        this.fPrev2 = 1;        // F_2
        this.fPrev1 = 2;        // F_3
        this.capacity = 3;      // F_4
        this.size = 0;
        this.shrinkBank = 0;
        this.table = new Object[this.capacity];
        this.credits = new int[this.capacity];
    }

    public void push(T item) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("ACTION: PUSH(" + item + ") [Fee Charged: 4 coins]");

        // 1. Check if expansion is required
        if (size == capacity) {
            expandTable();
        }

        // 2. Insert item: 1 coin pays for insertion, 3 coins saved on element
        table[size] = item;
        credits[size] = 3;
        size++;

        // 3. Display current bank status
        printStatus();
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Cannot pop from an empty table.");
        }

        System.out.println("\n--------------------------------------------------");
        System.out.println("ACTION: POP() [Fee Charged: 4 coins]");

        // 1. Remove item: 1 coin pays for deletion, 3 coins saved to shrink bank
        size--;
        T item = (T) table[size];
        table[size] = null;
        credits[size] = 0;
        shrinkBank += 3;

        // 2. Check if shrink condition met: n <= F_{k-2} (minimum capacity = 3)
        if (capacity > 3 && size <= fPrev2) {
            shrinkTable();
        }

        // 3. Display current bank status
        printStatus();
        return item;
    }

    private void expandTable() {
        int nextCapacity = capacity + fPrev1; // F_{k+1} = F_k + F_{k-1}
        System.out.println(">>> RESIZE (EXPAND): Capacity " + capacity + " -> " + nextCapacity);

        Object[] newTable = new Object[nextCapacity];
        int[] newCredits = new int[nextCapacity];

        // Copy elements and deduct 1 coin from each element's stack to pay copy cost
        for (int i = 0; i < size; i++) {
            newTable[i] = table[i];
            newCredits[i] = credits[i] - 1; // 1 coin spent per element copy
        }

        table = newTable;
        credits = newCredits;

        // Update Fibonacci pointers
        int oldCapacity = capacity;
        capacity = nextCapacity;
        fPrev2 = fPrev1;
        fPrev1 = oldCapacity;
    }

    private void shrinkTable() {
        int newCapacity = fPrev1; // Shrink to F_{k-1}
        System.out.println(">>> RESIZE (SHRINK): Capacity " + capacity + " -> " + newCapacity);

        Object[] newTable = new Object[newCapacity];
        int[] newCredits = new int[newCapacity];

        // Copy cost = size operations. Pay using shrinkBank first, then individual credits if needed.
        int copyCost = size;
        if (shrinkBank >= copyCost) {
            shrinkBank -= copyCost;
            for (int i = 0; i < size; i++) {
                newTable[i] = table[i];
                newCredits[i] = credits[i]; // Element credits preserved
            }
        } else {
            int remainder = copyCost - shrinkBank;
            shrinkBank = 0;
            for (int i = 0; i < size; i++) {
                newTable[i] = table[i];
                newCredits[i] = credits[i] - remainder; // Deduct remainder from elements
            }
        }

        table = newTable;
        credits = newCredits;

        // Update Fibonacci pointers backward
        capacity = newCapacity;
        fPrev1 = fPrev2;
        fPrev2 = capacity - fPrev1;
    }

    private int getTotalBankBalance() {
        int elementCreditsSum = 0;
        for (int i = 0; i < size; i++) {
            elementCreditsSum += credits[i];
        }
        return elementCreditsSum + shrinkBank;
    }

    private void printStatus() {
        int totalBank = getTotalBankBalance();
        int expansionCostNeeded = capacity;       // Needs F_k coins to copy on expand
        int shrinkCostNeeded = fPrev2;             // Needs F_{k-2} coins to copy on shrink

        System.out.print("Table Contents & Credits: [ ");
        for (int i = 0; i < size; i++) {
            System.out.print(table[i] + "(coins:" + credits[i] + ") ");
        }
        System.out.println("]");

        System.out.println("Table Size (n): " + size + " | Capacity (m): " + capacity);
        System.out.println("Bank Breakdown: Element Credits = " + (totalBank - shrinkBank)
                + " | Shrink Bank Reserve = " + shrinkBank
                + " | Total Bank = " + totalBank);

        // Check readiness for next possible operations
        boolean readyForExpand = totalBank >= expansionCostNeeded;
        boolean readyForShrink = totalBank >= shrinkCostNeeded;

        System.out.println("Expansion Readiness (Need " + expansionCostNeeded + " coins): "
                + (readyForExpand ? "ENOUGH CREDITS ✓" : "NOT ENOUGH ✗"));
        System.out.println("Shrink Readiness    (Need " + shrinkCostNeeded + " coins): "
                + (readyForShrink ? "ENOUGH CREDITS ✓" : "NOT ENOUGH ✗"));
    }

    // Demo trace
    public static void main(String[] args) {
        FibonacciDynamicTable<Integer> list = new FibonacciDynamicTable<>();

        // 1. Push elements past initial capacity to trigger expansion
        list.push(10);
        list.push(20);
        list.push(30); // Table is full (3/3)
        list.push(40); // Triggers Expand to 5
        list.push(50); // Table is full (5/5)
        list.push(60); // Triggers Expand to 8

        // 2. Pop elements down to trigger shrink
        list.pop();
        list.pop();
        list.pop(); // Size hits F_4 = 3 -> Triggers Shrink to 5
    }
}