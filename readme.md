Design considerations:-
All domain objects are immutable and thread safe. I have used ConcurrentHashMap for caching Order and User Object for scalability and thread safety.

OrderId generation is again thread safe with assumption of low contention among different threads.I am using OrderId to uniquely identify the Order
 as same user can add 2 or more different orders with same price and quantity.
 
Traversal of Orders to get OrderSummary by OrderType and Price will give view of Orders at or after creation of Iterator but will not throw ConcurrentModificationException, 
in case Order Cancellation happens at the same time.


Design Limitations:-

1. This solution has limitation that all Order/User data is stored in  memory. Application restart/crash will cause loosing it all. 
Ideally, creation of OrderId and persistence of Order, User and other Domain objects shall be in persistent store(e.g. oracle database)
2. LiverOrderBoard.summary() operation can have groupingBy and map operations in parallel (concurrently using stream.parallel()) provided number of orders
   are large and number of cores/processing power available. (Profiling needs to be done to ascertain the advantage/need of it).
