# Bank Management System

A console-based Java application modeling core banking operations — customer onboarding, account types, transactions, and loan processing — built to practice OOP fundamentals: inheritance, interfaces, custom exceptions, and encapsulation.

![java](https://img.shields.io/badge/java-21-orange) ![status](https://img.shields.io/badge/status-functional-brightgreen)

## Features

- Customer registration with input validation (custom exceptions for invalid name, address, contact number)
- Multiple account types: `SavingsAccount`, `CurrentAccount` (both extend a common `Account` base)
- Cashier and Admin roles implementing a shared `UserManagement` interface (login/logout)
- Loan handling via a `Loan` base class and `LoanService` interface, with EMI calculation
- Transaction logging and customer notifications

## Tech Stack
- Java 21
- No external dependencies — pure Java, compiled with `javac`

## Project Structure

```
bank-managment-system/
├── src/
│   ├── BankManagmentSystem.java   # Entry point — console menu
│   ├── Person.java                # Base class for people in the system
│   ├── Customer.java               # extends Person
│   ├── Admin.java                  # implements UserManagement
│   ├── Cashier.java                 # implements UserManagement
│   ├── Account.java                # Base account class
│   ├── SavingsAccount.java
│   ├── CurrentAccount.java
│   ├── Loan.java
│   ├── LoanAccount.java
│   ├── SavingLoan.java             # SavingsLoan — extends Loan
│   ├── LoanService.java            # interface
│   ├── UserManagement.java         # interface (login/logout contract)
│   ├── Transaction.java
│   ├── Notification.java
│   └── CustomExceptions.java       # InvalidNameException, InvalidAddressException, InvalidContactNumberException
└── README.md
```

## Running it

```bash
cd src
javac *.java
java BankManagmentSystem
```

Follow the on-screen menu to add customers, view them, or serve the current customer.

## Known limitations or Roadmap

- Data is in-memory only — nothing persists between runs
- Console-based; a GUI or web front end would be a natural next step
- Loan and account logic could be extended with more validation and interest-calculation edge cases

## Author
**Mahnoor Ishfaq** — [GitHub](https://github.com/mahnoorishfaq)
