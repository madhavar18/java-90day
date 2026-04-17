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

-`BankAccount` - private fields, constructor, deposit/withdraw with validation