# 📚 Java Collections Framework – Theory & Code Examples

This repository provides an overview of the **Java Collections Framework** along with practical code examples for commonly used collection classes like `ArrayList`, `HashSet`, `HashMap`, `LinkedList`, and more.

---

## 🧠 What is Java Collections Framework?

The **Java Collections Framework (JCF)** is a set of classes and interfaces that implement commonly reusable collection data structures like **lists, sets, maps, queues**, and more.

It provides:
- Standardized APIs
- Efficient data structures
- Algorithms for searching, sorting, and modifying data
- Interfaces like `List`, `Set`, `Map`, `Queue`
- Implementations like `ArrayList`, `HashSet`, `HashMap`, etc.

---

## 🗂️ Types of Collections

| Interface | Implementation | Description |
|----------|----------------|-------------|
| `List`   | `ArrayList`, `LinkedList` | Ordered collection that allows duplicates |
| `Set`    | `HashSet`, `TreeSet` | Unordered collection that doesn’t allow duplicates |
| `Map`    | `HashMap`, `TreeMap` | Key-value pairs |
| `Queue`  | `LinkedList`, `PriorityQueue` | FIFO data structure |

---

## 📘 Code Examples

### 1. ✅ `ArrayList`
A resizable array implementation of the List interface.

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Mango");
        list.remove("Banana");

        for (String fruit : list) {
            System.out.println(fruit);
        }
    }
}
