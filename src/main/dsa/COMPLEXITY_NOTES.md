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

## Sliding Window pattern
Template: left pointer + right pointer + state (HashMap or array)
- Expand right: add element to state
- Shrink left while invalid: remove element from state
- Update answer after each valid state

WHY O(n): each element enters window once, leaves window once = 2n ops total

## Problems solved
| Day | Problem         | Pattern      | Time | Space |
|-----|-----------------|--------------|------|-------|
| 9   | Two Sum (#1)    | HashMap      | O(n) | O(n)  |
| 10  | Longest Substring No Repeat (#3)  | Sliding Window | O(n) | O(1)* |
| 10  | Valid Anagram (#242)              | Freq Array     | O(n) | O(1)  |
| 10  | Contains Duplicate (#217)         | HashSet        | O(n) | O(n)  |