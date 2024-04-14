# NutriSprint-OOP
Applying Object Oriented principles into game development 
View full report here: (https://docs.google.com/document/d/1TX0IZU3Te0VXxRRu-O1w7n8g4z7jQlgmWvDc0e23c9U/edit?usp=sharing)

![NutriSprint](/assets/nutrisprint.png)

## Application Code
### Technology Stack
Language: Java 
Tools used: LibGDX    

## Encapsulation
Encapsulation prevents the code and data within an object from being accessed by other code defined outside the class. Access modifiers such as Private and Protected are used to keep functionality encapsulated within their own scope and packages. Public Getters and Setters allow the behaviour of the encapsulated object to be known to other objects without revealing the inner workings.

![Encapsulation](/assets/encapsulation.png)

## Inheritance
Common functionality is declared within a single parent class and gets inherited to children classes, which add on their own functions based on their specific jobs.

![Inheritance](/assets/inheritance.png)

## Polymorphism
Method overloading is done by creating multiple methods with the same name but to fit different use cases. Method overriding is done by firstly having a default implementation of a method in the parent class and having the subclass override it by specifying another implementation of the method.

![Polymorphism](/assets/polymorphism.png)

## Abstraction
Abstraction allows us to hide the specific and complex implementation details of a class or method and only expose the necessary features of an object. Through this, it will be easier for its subclass to interact with the method and write its own specific implementation without needing to understand its internal complexities. 

![Abstraction](/assets/abstraction.png)

## Singleton Pattern
A singleton pattern is defined as only having one instance of the class being created and then passed as reference if it were to be used throughout the lifecycle of the game. Since the singleton pattern violates the single responsibility principle, we have made sure to only use it when necessary. 

![Singleton](/assets/singleton.png)

## Factory Pattern
The factory pattern provides a method of creating objects without specifying the exact class used to create it and hides the construction of a single object. Using a factory makes our system more loosely coupled and  allows for the possibility of introducing new classes without altering the code which utilises the factory.

![Factory](/assets/factory.png)

## Observer Pattern
This pattern involves two participant classes, the Observer and the Subject. The Subject maintains a list of its dependents, called Observers, and notifies them of any state changes, usually by calling one of their methods.

![Observer](/assets/observer.png)

## SOLID Principles
We made sure that all our classes only have a single responsibility and is open to extension, as this will greatly help in the development phase as the product scales up and when there are many other classes.

## Reflection
All in all, the team learnt a lot through this project and we would definitely bring over our knowledge on object oriented principles in our future work as well.
