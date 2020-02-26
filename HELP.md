# Know Your Customer technical test

My solution to the know your customer technical test.

###Notes
Although the instructions ask to maintain a list of strings ids I think it's better to map a bidirectional many to many relationship here of
customers and accounts objects. The id can still be retrieved from the object if required.

##REST URLS

### List of accounts by customer
http://localhost:8080/accountsByCustomerId?customerId=1
### List of customers by account number
http://localhost:8080/customersByAccountNumber?accountNumber=123
### Add account to existing customer
http://localhost:8080/addAccount?customerId=1&accountNumber=7890

#Docker
###Build Image
    docker build .
###Run Image
    docker run -p 8080:8080 <container_id>