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

-`BankAccount` - private fields, constructor, deposit/withdraw with validation.


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

-`BankAccount` - final fields, constructor 2, static fields, getters for static fields.

### Packages built today
-`tests` - contains every day changes as test classes. 