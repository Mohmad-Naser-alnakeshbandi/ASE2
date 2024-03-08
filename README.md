# Advance Software Engineering
## based on Domain Driven Design

Principles:
- SRP "Single-responsibility principle": Value object for the entities, in this way no validation in the entity class
  - Clearly defined task for each object
  - Higher-level behavior through the interaction of objects
  - Low coupling and complexity
  - Recognize more responsibility 
- ISP:
  - ISP is intended to keep a system decoupled and thus easier to refactor, change, and redeploy
  - Not to write all method in one interface if you only want to implement 2 of them
- DRY "Don't repeat yourself":
  - reducing repetition of information which is likely to change, replacing it with abstractions that are less likely to change, or using data normalization which avoids redundancy in the first place.
- Polymorphism 
  - for the interface -> Declare methods with different object that I pass. => The Common Package maybe!