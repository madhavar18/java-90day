# DSA Complexity Reference

## Big O — the mental model
Not "how fast?" but "how does runtime GROW as input grows?"

## Complexity classes
| Notation   | Name          | Example                    | n=1M ops    |
|------------|---------------|----------------------------|-------------|
| O(1)       | Constant      | HashMap get, array[i]      | 1           |
| O(log n)   | Logarithmic   | Binary search              | ~20         |
| O(n)       | Linear        | Single loop, linear scan   | 1,000,000   |
| O(n log n) | Linearithmic  | Merge sort, heap sort      | ~20,000,000 |
| O(n²)      | Quadratic     | Nested loops               | 10¹²        |
| O(2ⁿ)      | Exponential   | Brute force subsets        | never       |

## Array operations (ArrayList)
| Operation         | Time |
|-------------------|------|
| get(index)        | O(1) |
| contains(value)   | O(n) |
| add(end)          | O(1) amortised |
| add(middle)       | O(n) |
| remove(middle)    | O(n) |

## String operations
| Operation              | Time       | Notes                          |
|------------------------|------------|--------------------------------|
| charAt(i)              | O(1)       | direct index                   |
| length()               | O(1)       | cached                         |
| substring(i,j)         | O(j-i)     | copies chars — not O(1)!       |
| equals()               | O(n)       | char by char comparison        |
| + concatenation        | O(n)       | creates new String object      |
| StringBuilder.append() | O(1) amort | same as ArrayList.add()        |

## LinkedList operations
| Operation          | Time  | WHY                                      |
|--------------------|-------|------------------------------------------|
| addFirst()         | O(1)  | 2 pointer ops, no shifting               |
| addLast()          | O(n)  | must walk to end                         |
| addAt(i)           | O(n)  | walk to position + O(1) rewire           |
| removeFirst()      | O(1)  | 1 pointer op                             |
| removeLast()       | O(n)  | must walk to second-to-last              |
| get(i)             | O(n)  | must walk from head                      |
| contains(v)        | O(n)  | must scan every node                     |
| reverse()          | O(n)  | one pass, 3 pointer ops per node         |
| findMiddle()       | O(n)  | fast/slow pointer, one pass              |

## ArrayList vs LinkedList — decision guide
| Scenario                      | Use          |
|-------------------------------|--------------|
| Random access by index        | ArrayList    |
| Add/remove at END             | ArrayList    |
| Add/remove at FRONT           | LinkedList   |
| Unknown size, general use     | ArrayList    |
| Queue/Deque implementation    | LinkedList   |

## Stack operations (ArrayList-backed)
| Operation | Time         | Notes                        |
|-----------|--------------|------------------------------|
| push()    | O(1) amort   | add to end of ArrayList      |
| pop()     | O(1)         | remove from end              |
| peek()    | O(1)         | read end without removing    |

## Queue operations (LinkedList-backed with tail pointer)
| Operation    | Time | Notes                           |
|--------------|------|---------------------------------|
| enqueue()    | O(1) | add to back via tail pointer    |
| dequeue()    | O(1) | remove from front, move head    |
| peekFront()  | O(1) | read front without removing     |

## When to use Stack vs Queue
Stack (LIFO): matching problems, undo history, DFS traversal, call stack modelling
Queue (FIFO): BFS traversal, scheduling, print queues, notification systems

## Sliding Window pattern
Template: left pointer + right pointer + state (HashMap or array)
- Expand right: add element to state
- Shrink left while invalid: remove element from state
- Update answer after each valid state

WHY O(n): each element enters window once, leaves window once = 2n ops total

## Binary Search
Precondition: array MUST be sorted
Time: O(log n) — halves search space each step
Space: O(1) iterative, O(log n) recursive (call stack)

Invariant: target is always within [left, right] if it exists.
When left > right: search space empty, not found.

Integer overflow trap: use left + (right - left) / 2, NOT (left + right) / 2

## Recursion + Memoization
Naive recursion: often O(2ⁿ) — recomputes same subproblems
Memoization: cache results → O(n) time, O(n) space
Iterative (bottom-up): O(n) time, O(1) space — usually best

## Binary Search Tree
BST property: left subtree < node < right subtree (maintained at EVERY node)

| Operation | Balanced (avg) | Unbalanced (worst) |
|-----------|---------------|--------------------|
| insert()  | O(log n)      | O(n) sorted input  |
| search()  | O(log n)      | O(n)               |
| delete()  | O(log n)      | O(n)               |

Unbalanced worst case: inserting sorted data creates a linked list shape.
Fix: self-balancing trees (AVL, Red-Black) — Day 52 system design context.

## Tree Traversals
| Traversal | Order              | Uses                           | DS Used |
|-----------|--------------------|--------------------------------|---------|
| Inorder   | left, node, right  | Sorted output from BST         | Stack   |
| Preorder  | node, left, right  | Tree serialisation, copy       | Stack   |
| Postorder | left, right, node  | Delete tree, subtree sizes     | Stack   |
| BFS/Level | level by level     | Shortest path, level problems  | Queue   |

## Problems solved
| Day | Problem         | Pattern      | Time | Space |
|-----|-----------------|--------------|------|-------|
| 9   | Two Sum (#1)    | HashMap      | O(n) | O(n)  |
| 10  | Longest Substring No Repeat (#3)  | Sliding Window | O(n) | O(1)* |
| 10  | Valid Anagram (#242)              | Freq Array     | O(n) | O(1)  |
| 10  | Contains Duplicate (#217)         | HashSet        | O(n) | O(n)  |
| 15  | Reverse Linked List (#206)| 3-pointer reversal  | O(n) | O(1)  |
| 15  | Middle of LinkedList(#876)| Fast/slow pointers  | O(n) | O(1)  |
| 16  | Valid Parentheses (#20)      | Stack            | O(n) | O(n)  |
| 16  | Daily Temperatures (#739)    | Monotonic Stack  | O(n) | O(n)  |
| 20  | Binary Search (#704)        | Binary Search    | O(log n) | O(1)     |
| 20  | First Bad Version (#278)    | Binary Search    | O(log n) | O(1)     |
| 22  | Max Depth (#104)              | DFS postorder  | O(n)     | O(h)   |
| 22  | Invert Binary Tree (#226)     | DFS preorder   | O(n)     | O(h)   |
| 22  | Level Order Traversal (#102)  | BFS + Queue    | O(n)     | O(w)   |
| 22  | Validate BST (#98)            | DFS + range    | O(n)     | O(h)   |