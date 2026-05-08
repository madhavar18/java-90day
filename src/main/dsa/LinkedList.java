package dsa;
/**
 * Day 15 — Singly LinkedList built from scratch
 *
 * WHY build from scratch instead of using java.util.LinkedList:
 * Because the JVM's implementation hides the pointer manipulation.
 * You need to SEE the pointer changes to understand what's happening.
 * After building this, java.util.LinkedList has no mysteries.
 *
 * A singly linked list: each node points to the NEXT node only.
 * You can traverse forward but not backward.
 * (Doubly linked list — next AND prev pointers — is java.util.LinkedList's actual impl)
 */
public class LinkedList<T> {
    // ── Node: the building block ────────────────────────────────────
    // WHY a nested static class:
    // Node only makes sense inside LinkedList — it's an implementation detail.
    // Static because Node doesn't need access to LinkedList's instance fields.
    // Generic <T> so this LinkedList works with any type — String, Integer, BankAccount.
    private static class Node<T> {
        T data; // the actual value stored
        Node<T> next; // reference to the next node (null if last)

        Node(T data) {
            this.data = data;
            this.next = null; // new nodes start unconnected
        }
    }

    // ── LinkedList fields ───────────────────────────────────────────
    private Node<T> head; // reference to the first node (null if empty)
    private int size; // track size separately - O(1) size() instead of O(n) traversal

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // ── ADD OPERATIONS ──────────────────────────────────────────────

    // Add to front — O(1)
    // WHY: two pointer operations regardless of list size
    // This is LinkedList's primary advantage over ArrayList
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head; // new node points to current first node
        head = newNode; // head now points to new node
        size++;

        // Memory picture after addFirst("X") on [A→B→C]:
        // HEAD → [X] → [A] → [B] → [C] → null
        // Two pointer assignments. Always two. O(1).
    }

    // Add to end — O(n)
    // WHY O(n): must walk to the last node first
    // ArrayList's addLast is O(1) — this is ArrayList's advantage
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);

        if(head == null) {
            head = newNode;
            size++;
            return;
        }

        // Walk to the last node
        // WHY 'current.next != null' not 'current != null':
        // We want to STOP at the last node (next == null), not past it.
        // If we walked until current == null, we'd lose the reference to the last node.
        Node<T> current = head;
        while(current.next != null) {
            current = current.next;
        }
        // current is now the last node
        current.next = newNode;
        size++;
    }

    // Add at specific index — O(n) to reach position, O(1) to insert
    public void addAt(int index, T data) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if(index == 0) {
            addFirst(data);
            return;
        }

        Node<T> newNode = new Node<>(data);
        Node<T> current = head;

        // Walk to the node BEFORE the insertion point
        // We need the node at (index-1) so we can set its .next
        for(int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        // current is now at index-1
        // Rewire: newNode points to what current pointed to, then current points to newNode
        newNode.next = current.next; // Step 1: newNode → old next
        current.next = newNode; // Step 2: current → newNode

        // WHY this order? If you did Step 2 first, you'd lose the reference to old next.
        // current.next = newNode;    // now current.next IS newNode
        // newNode.next = current.next; // this sets newNode.next = newNode — circular reference!
        // Order of pointer manipulation matters. Always attach the new node's outgoing
        // pointer BEFORE redirecting the incoming pointer to the new node.
        size++;
    }

    // ── REMOVE OPERATIONS ───────────────────────────────────────────

    // Remove from front — O(1)
    public T removeFirst() {
        if(head == null) throw new RuntimeException("List is empty");

        T data = head.data;
        head = head.next;
        size--;
        return data;
        // The old first node is now unreferenced — JVM garbage collector reclaims it
    }

    // Remove from end - O(n)
    public T removeLast() {
        if(head == null) throw new RuntimeException("List is empty");
        if(head.next == null) {
            T data = head.data;
            head = null;
            size--;
            return data;
        }

        // Walk to the second-to-last node
        Node<T> current = head;
        while(current.next.next != null) {
            current = current.next;
        }
        // current is now the second-to-last node
        T data = current.next.data;
        current.next = null; // disconnect the last node
        size--;
        return data;
    }

    // Remove by value - O(n)
    public boolean remove(T data) {
        if(head == null) return false;

        // special case: removing the head
        if(head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        // Walk until we find a node whose NEXT contains the target
        // WHY check current.next: we need the node BEFORE the target
        // to rewire its .next pointer, skipping the target
        Node<T> current = head;
        while(current.next != null) {
            if(current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current= current.next;
        }
        return false; // not found;
    }

    // ── ACCESS OPERATIONS ────────────────────────────────────────────

    // Get by index — O(n): must walk from head
    public T get(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node<T> current = head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Contains - O(n): must scan every node
    public boolean contains(T data) {
        Node<T> current = head;
        while(current != null) {
            if(current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    // ── UTILITY ─────────────────────────────────────────────────────

    // Reverse the list in-place — O(n)
    // This is LeetCode #206 — one of the most common LinkedList interview problems
    // WHY in-place: no new nodes created, just pointer rewiring
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;

        // Walk through the list, reversing each node's pointer
        // [A→B→C→D→null] becomes [null←A←B←C←D] = [D→C→B→A→null]
        while(current != null) {
            next = current.next; // save next before we overwrite it
            current.next = prev; // reverse the pointer
            prev = current; // prev advances
            current = next; // current advances
        }
        head = prev; // prev is now the last node we processed = new head
    }

    // Find the middle node - O(n)
    // uses fast/slow pointer technique (Floyd's algorithm simplified)
    // Fast moves 2 steps, slow moves 1 step
    // When fast reaches end, slow is at middle
    public T findMiddle() {
        if(head == null) throw new RuntimeException("List is empty");

        Node<T> slow = head;
        Node<T> fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next; // 1 step
            fast = fast.next.next; // 2 steps
        }
        return slow.data;
        // WHY this works: fast covers 2n distance while slow covers n distance.
        // When fast reaches the end (n total nodes), slow is at n/2 = middle.
        // No counting needed, no second pass, one traversal = O(n).
    }

    @Override
    public String toString() {
        if(head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while(current != null) {
            sb.append(current.data);
            if(current.next != null) sb.append(" -> ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
        // WHY StringBuilder: same reason as Day 10.
        // Building a String in a loop with + is O(n²).
        // StringBuilder.append is O(1) amortised = O(n) total.
    }
}
