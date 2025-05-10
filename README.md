# BU.ZE

Budget-zen

This application helps you managing your monthly family budget.

Accounting can be fun and simple and this application aims at getting you as a user, on the right track
in saving more money every month so that you can afford some extra more often.

Download the application and start recording your first financial transactions.
As the amount of records grows up, you will be able to spot where you can save money, how much tax you are paying every month, what are the mandatory expenses, and become better at making the decision of buying or not the next item you saw on TV.

The application will give you reports and statistics that will help figure out whether you are on a good sport or you have to make some decisions.

Disclaimer:

A lot of the terms and wording are stolen from the financial and accounting world. This application is meant to be simple but complete.
Although the application doesn't follow the double-entry bookkeeping method at the moment, the final objective - in the long term - is to make this application reliable for small businesses as well.
As I am building this application I have to make a solid basement for further developments that will lean towards implementing double-entry bookkeeping, in order to fulfill the final objective.
I would then have a simple version for families and a more advanced / professional version for small businesses.

## Your data is yours

You don't have to register to our website.

You don't have to give us your email address, nor your phone number and your name.

Just download the app, install it on your computer, and everything you record within the app stays on your computer.

My goal is to keep growing as a developer and make a free application that is really useful for everyone.

## Empower yourself

Take control of your budget easily.

When you first launch the application you will be prompted to add a journal in order to records your transactions.
Once created, you can start recording journal entries (financial transactions).
The more detail you give to your journal, the more precise will be the reports and forecasts. 

## Definitions

Here are some definitions that will make sure everyone is on the same page.

### A Journal

A journal is ... is a detailed running record of all financial transactions. The journal includes information like the transaction date, (the accounts affected),
the amount of the transaction. This accounting concept is often associated with double§entry bookkeeping method.

In our application a journal has the following attributes:

* UUID
* Name
* Type
* Balance


### A Journal Entry

A journal entry is the act of keeping or making records of any transactions.
It can be either an income, which will increase the balance of your journal, or an expense which will decrease the journal balance.

In double-entry bookkeeping method, a journal entry can consist in several recordings, each of which is either a credit or a debit.
The total of the debits must equal the total credits, or the journal entry is considered unbalanced.
A properly documented journal entry consists of the correct date, amount(s) that will be debited, amount that will be credited, narration of the transaction, and unique reference number (i.e. check number).

In our application a journal entry has the following attributes:

* UUID
* Date
* Description
* Amount allocation
* Mean of payment
* Total amount
* Reference number
* Payment occurrence

#### Accounting allocation

In french, this is called "ventilation comptable" which can be translated into "accounting allocation" or "accounting venting".
This is the act of allocating, separating or spreading the cost of a service or a product throughout several accounts.
This is not mandatory nor a legal obligation, it is just a tool that helps strategic decision making and precise reporting.

Example:

I go to the grocery store and I need to buy food and a hair-dryer.
Let's say the total invoice is 75 USD.
The hair-fryer cost 45 USD and the food cost 30 USD.
Instead of recording 2 journal entries, I can record 1 journal entry and allocate the costs to different category or accounts.
I will allocate 45 USD to the house equipment account or category and 30 USD to the food account or category.
This way I can get a better overview of where I spend the money and where I can save money.

In our application, accounting allocation is defined as follows:

* Journal entry UUID
* Amount
* Currency
* Category / affected account

#### Payment occurrence

#### Mean of payment

#### Category / Affected account

