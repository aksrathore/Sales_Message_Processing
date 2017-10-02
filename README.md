## JPMorgan
Small Message Processing Application

## Problem
Implement a small message processing application that satisfies the below requirements for processing sales notification messages. You should assume that an external company will be sending you the input messages, but for the purposes of this exercise you are free to define the interfaces.
Processing requirements
- All sales must be recorded
- All messages must be processed
- After every 10th message received your application should log a report detailing the number of sales of each product and their total value.
- After 50 messages, your application should log that it is pausing, stop accepting new messages and log a report of the adjustments that have been made to each sale type while the application was running.
Sales and Messages
- A sale has a product type field and a value – you should choose sensible types for these.
- Any number of different product types can be expected. There is no fixed set.
- A message notifying you of a sale could be one of the following types
1. Message Type 1 – contains the details of 1 sale E.g. apple at 10p
2. Message Type 2 – contains the details of a sale and the number of occurrences of that sale. E.g 20 sales of apples at 10p each.
3. Message Type 3 – contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. Operations can be add, subtract, or multiply e.g Add 20p apples would instruct your application to add 20p to each sale of apples you have recorded.
  
  
## Approach
 
The SalesProcessor Class is the main program which will read inputs from an external text file. It passes the messages to the static method processMessage() of Sales Class. The message is then sent to the MessageParser Class from the Sales Class which parses the message based on the pattern and at runtime the strategy is decided which class will process the message.

I tried to follow SOLID Principles as much as possible.
 
## Assumptions 
 
- I have taken only fruits sale items.
- The messages are dealt at the runtime with regex pattern for 3 message types. If in future the message pattern is changed a new regex can be added by just adding a class without adding making any change in the other classes in the application.
- The incoming messages are of the type e.g.; 
1. Message Type 1 : "apple at 10p"                   : ^([A-z]+) at (\\d+p)$
2. Message Type 2 : "20 sales of apples at 10p each" : ^(\\d+) sales of ([a-z]+) at (\\d+p) each$
3. Message Type 3 : "Add 20p apples"                 : ^([A-Z][a-z]+) (\\d+p) ([a-z]+)$
- After processing 50 messages, the application asks for user input to process further
- No Explicit Logging is provided. All the messages are printed on the console. To avoid using third party libraries and keeping the application simple, only simple print statement is used. All the error messages are printed using System.err.
 
## Algorithm
1. Each line is read from the message stream text file and processed to find out the product type, price, units and operation performed on the price of the units of the product.
2. Strategy Pattern is used to determine the message type so that if the pattern is changed only a class needs to be added or removed but no modification is needed in any other classes.
3. A HashMap is used to store the product details which gets updated if any new sales occur. The product type (e.g., apple) is used as the key and the Product POJO Object is the value. This data structure is used to generate periodic sales report after every 10 sales process.
4. The invalid message pattern is kept in a list which gets printed after every periodic sales report.
5. Adjustment operations are performed and respective log is generated and store in a list in the Sales.java. The list is used after every 50th message processing to print the Adjustment Operation Report.
6. After 50th message being processed, the processing is paused to ask for user intervention to proceed furthr or end processing.
   
## To do
- use proper logging of the report
- write customised Exceptions
- write unit test cases
- make it more robust to process inputs from any interface (e.g.,  JSON). Although proper care is taken to reduce the dependency on any other classes. Like for JSON we just have to add extra methods in the Sales.java and MessageParser Context class to deal with JSON format.
- take care of synchronization or serializations

