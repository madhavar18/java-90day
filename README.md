# java-90day

90-day Java Learning journey - Software Engineering track.

## Day 1 - Java OOP: classes, objects, encapsulation

### What I built
A Bank Account Simulator CLI in java demonstrating OOP fundamentals

### What I Learned

- Why OOP exists: procedural code has no way to protect data from being corrupted by unrelated functions. Classes bundle data  with the methods that operate on it.
- Why fields are private: encapsulation ensures no external code can set `balance = -99999`. Only the class itself can mutate its own state. 
- Why `this` keyword: disambiguates between a constructor parameter and the object's field when they share the same name.

### Classes built today

- `BankAccount` - private fields, constructor, deposit/withdraw with validation.


## Day 2 - Java OOP: this, static, final keywords & constructors and their overloading

### What I built
A Bank Account Simulator CLI in java demonstrating OOP fundamentals

### What I Learned

- Why constructors exist: to initialize objects. because, objects without initial state are broken objects.
- Constructor overloading: same name, but different parameters
- Constructor delegation: using this(), we can call the first constructor. but why?, because it supports in one source of truth.
- this keyword: used to reference an object or object's properties. 3 use cases - field disambiguation, constructor delegation, passing self as an argument.
- static keyword: it is used to declare properties that are shared across all instances, but owned by the class itself. 
- final keyword: it is used to declare properties that don't need to be reassigned.

### Classes built today

- `BankAccount` - final fields, constructor 2, static fields, getters for static fields.
- `Day01Test`: tests day-1's changes made.
- `Day02Test`: tests day-2's changes made.

### Packages built today
- `tests` - contains every day changes as test classes. 


## Day 3 - Java OOP: Inheritance and super()

### What I built
A Bank Account Simulator CLI in java demonstrating OOP fundamentals

### What I Learned

- Inheritance: put the shared code in one place(the parent), and only write what's different in the child class.
- IS-A: Inheritance & HAS-A: composition
- super: "super" is to the parent class what "this" is to current class
- Method overriding: `@Override` - is an annotation, to override parent class methods
- when you want both child's behavior and parent's behavior - use `super.methodName(args)` inside the overridden method.

### Classes built today

- `SavingsAccount`: extends `BankAccount`
- `CheckingAccount`: extends `BankAccount`, but overrides the `withdraw()` method
- `Day03Test`: tests day-3's changes made.


## Day 4 - Java OOP: Polymorphism, Interfaces and Abstract classes

### What I built
A Bank Account Simulator CLI in java demonstrating OOP fundamentals

### What I Learned

- Polymorphism: One method call, many possible behaviors, decided at runtime based on the actual object type.
- Interface: a pure contract, it says something must be able to do, with zero implementation. Any class can implement any no. of interfaces.
- Abstract Classes: the middle ground, use when you want to provide some shared implementation & enable that subclasses provide certain methods - but the parent class should never be instantiated.

### Classes built today

- `Printable`: An interface implemented by `SavingsAccount`.
- `Auditable`: An interface implemented by both `SavingsAccount` and `CheckingAccount`.

### NOTE: 
SINCE `BankAccount` IS MADE "ABSTRACT", INSTANTIATING IT IS NOT POSSIBLE. SO, THE TEST CLASSES OF DAY01 AND DAY02 MIGHT NOT WORK AND ARE TURNED INTO COMMENTS FOR THE EXECUTION OF TEST CLASS OF DAY04. INORDER TO TEST DAY01 AND DAY02, REVERT BACK `BankAccount` TO NORMAL CLASS.


## Day 5 - Java Collections

### What I built
A Bank Account Simulator CLI in java demonstrating OOP fundamentals

### What I Learned

- Why Collections? : they solve the problems caused by arrays, like limited space and data type compatibility issues etc.,
- Type of Collections: Lists - for contiguous memory allocations, Map - to store key-value pairs and for O(1) fetch time, Set - Hashed storage and uniqueness check.

### Classes built today

- `BankRepository`: Collection data types required for this bank simulator and helper functions for using them.
- `Day05Test`: to test day-5's changes.


## Day 9 - Big O & Two Sum 

### What I Learned

- Why Big O? : Big O is about answering: "as the input grows, how does the runtime grow?".
- Array Operations and their complexity.
- Two Sum: the difference between the brute force approach - O(n2) and optimal approach with HashMap - O(n).

### Classes built today

- `Two Sum.java`: implementation of both brute force and optimal approaches along with complexity comparison of the approaches.


## Day 10 - Strings & Sliding Window Pattern

### What I Learned

- Why are Strings immutable in Java? : 3 reasons - security, performance, thread safety.
- String Operations and their complexity.
- Sliding Window Pattern: Maintain two pointer - left & right - that define the current window.  the window represents our current candidate substring.  Expand right to add new characters, when the window becomes invalid, shrink left until its valid again.


### Classes built today

- `StringProblems.java`: implementation of Sliding window pattern and complexity benchmark comparison between new string object formation and StringBuilder class. 