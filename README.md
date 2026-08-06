# ug-exam-optimizer
University of Ghana Examination Materials Distribution Optimizer is a Java 17 desktop/backend system designed to model, route, and manage the logistics of examination material distribution across campus examination centers.

---

## 🚀 Project Overview

Managing and distributing examination materials to multiple halls across campus requires strict timeliness, reliable verification, and fast lookups. This system optimizes the logistics pipeline by implementing custom core data structures to handle:

* **Fast Exam Code & Batch Lookups:** Using custom Binary Search Trees (BST) and Red-Black Trees (RBT).
* **Disk-Based Indexing & Data Persistence:** Using B-Trees and an embedded SQLite database.
* **Optimal Logistics Routing:** Using Graphs (Adjacency Lists & Matrices) and Disjoint Sets to calculate distribution paths across exam centers.
* **Dispatch Scheduling & Queueing:** Utilizing Priority Queues, Heaps, Deques, and Circular Queues for handling distribution priority based on exam start times.

---

## 🛠️ Tech Stack & Tools

* **Language:** Java 17 (LTS)
* **Database:** SQLite
* **Version Control:** Git & GitHub

---

## 🧪 Building and Testing

This project currently uses plain `javac`/`java`. The JUnit Platform Console Standalone jar needed to run tests is committed under `lib/`, so no setup step is required — just compile and run the test suite:

```bash
javac --release 17 -d out/main $(find src/main/java -name "*.java")
javac --release 17 -cp "out/main;lib/junit-platform-console-standalone-1.10.3.jar" -d out/test $(find tests -name "*.java")
java -jar lib/junit-platform-console-standalone-1.10.3.jar execute --class-path "out/main;out/test" --scan-class-path --details=tree
```

A successful run ends with `0 tests failed`.

---
