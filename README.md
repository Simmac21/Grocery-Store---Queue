# Grocery-Store---Queue

A program that attempts to reduce the time it takes to process customers in grocery store check-out lines.
The overall objective is to place clients in the best possible checkout line at all times.
This will be determined by the overall number of consumers as well as the quantity of things in each cart that needs to be processed.

The following formula is used to calculate the time it takes to serve a single customer:
t = 45 + 5*ni;
where: t, time it takes to serve a customer in seconds,
ni, the number of items in the customers basket.

A store has one or more check-out lines, which are defined as follows:
• n = number of normal check-out lines
• f = number of express check-out lines (less than or equal to x items)
There could be up to four fast check-out lines at a store.
Customers may have a large number of products in their shopping basket or simply a few.
Each new customer will be added to the check-out line with the cumulative least amount of time in front of it (the shortest line). 
If the customer's cart has less than or equal to x items, they can enter one of the f express lines.
It should be emphasised that they are not required to use an express line if a different check-out line would provide a faster service time.
Customers who have more than x goods must use one of the store's other n check-out lines. 
The total number of check-out lines will be equal to n + f;

