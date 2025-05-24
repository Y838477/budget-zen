# Budget-Zen

Budget-Zen is a simple and privacy-focused family budgeting app designed to help you record and manage all your incoming and outgoing payments throughout the month.

---

## 🔐 Privacy by Design

Budget-Zen does **not** collect any personal information.  
We strongly encourage users to use only generic and non-identifiable names when creating payment methods or transaction journals.

**Example:**
- "Visa CB John" → Use "Visa CB"
- "Savings Account #1" → Use "Savings #1"

All data remains **completely under your control** and is stored **locally on your device**.

---

## 📘 Transaction Journals

Before you can start managing your finances, you’ll need to create a **Transaction Journal**.  
A journal works like a ledger, recording all income and expenses for a specific period (typically one month).

### Each journal includes:
- A unique identifier (UUID)
- A name
- A category (e.g., "Checking Account", "Savings")
- A period (e.g., May 2025)
- An initial balance
- A list of transactions

The **category** of the journal reflects the nature of the account being tracked and helps generate accurate reports later.

**Examples of categories:**
- Checking Account
- Savings Account

Categorizing journals allows for more advanced budgeting features, such as generating savings reports or automating transfers between checking and savings accounts.

---

## 💸 Recording Payments

When a journal is created, the balance is initialized to a user-defined amount.  
Each new transaction will **increase (income)** or **decrease (expense)** the balance.

At the end of each month, the closing balance is carried over to the next month's journal.

### Types of transactions:
- One-time
- Recurring (monthly, quarterly, semi-annually, annually)

### A valid transaction must include:
- A date
- A payee or recipient
- A payment method
- An amount

### Transactions may be:
- Expense
- Income
- Refund

### Accepted payment methods:
- Check
- Cash
- Credit/Debit Card
- Bank Transfer
- Direct Debit

For credit/debit card transactions, you can register multiple cards in the app.  
When entering a payment, you’ll be prompted to select the appropriate card from your saved list.

Each payment entry can optionally include a **description or note**, such as:
- Check number
- Contract number for a subscription
- “Pending refund”
- “Amazing dinner at the restaurant”

### Each transaction is defined by:
- A UUID
- Date
- Category (income or expense)
- Payee/Payer
- Payment method
- Amount
- Description / Reference
- Frequency (e.g., monthly, annually)

---

## 📊 Forecasting & Dashboard

Budget-Zen gives you a clear view of your monthly finances through a smart dashboard.

You can classify your expenses into:
- **Essential**
- **Useful**
- **Nice to Have**
- **Unnecessary**

This classification helps users **make better decisions** at a glance.

Some default categories are pre-defined in the app to assist with sorting transactions and providing clarity on financial priorities.

### Default Income Categories:
- Salary
- Pension
- Government Allowance
- Real Estate Income
- Donations Received

### Default Expense Categories:
- Housing
- Food & Groceries
- Subscriptions
- Entertainment
- Vacations
- Sports
- Transportation
- Taxes
- Bank Fees
- Tuition
- Charitable Donations
- Gifts

---

## 🚀 Getting Started

1. Download and install Budget-Zen.
2. Create your first Transaction Journal.
3. Record your income and expenses.
4. Monitor your balance and make informed budgeting decisions.

---

**Stay in control. Stay Zen.**  
_Manage your family budget with simplicity and privacy._
