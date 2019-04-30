RestFul webservice which takes jason req and converts it to Xml.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Jason req to Rest webservice:

{
  "Customer": {
    "id": "123",
    "name": "Ravi"
  }
}

response:

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Customer>
    <id>123</id>
    <name>Ravi</name>
</Customer>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 camel:run command

From postman Test the service using:http://localhost:9090/route/customerservice/customers (POST)

