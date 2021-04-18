## Demo Challenge

#### Instructions
1. Complete the project setup as listed below
2. Complete the Excerise
3. Email a synopsis of your work and the link to your git repo containing the completed exercise to: sqedemonstrationchallenge@nbcuni.com


#### Expectations
We will be evaluating
1. Naming conventions
2. Code readability
3. Code encapsulation
4. Code structure and organization
5. Quality of test cases
6. Variety  of testing types (examples: boundary, happy path, negative, etc) 


#### Technologies
1. Java
2. Selenium
3. TestNG
4. Any other technologies you see fit.
5. Please do not use a BDD framework.

#### Project Setup
1. Clone this project to your git account in a public repo
2. Setup the project in your IDE
3. Open the index.html file from src/test/resource/files in a browser
4. Copy the url from the browser and update the url value in src/test/resource/config.properties to be the copied url.
5. In src/test/resources update the config.properties file platform for your OS.
6. From command line run mvn clean install -U -DskipTests
7. Make sure you can run the DemoTest and chrome launches.  You may need to update the chromedriver in /src/test/resources/chromedriver/ to the version that works with your browser
   https://chromedriver.chromium.org/


#### Exercise
1. Use the site at the index.html
2. There are helper locators provided for you in the src/test/resource/files/locators.txt file.
3. In the Test Cases section below:
  - List all of the test cases you think are necessary to test the sample page
  - Note any defects or issues observed
4. Code up a few examples of:
  - At least one happy path case placing an order
  - At least one error case
5. When complete please check your code into your public git repo

#### Test Cases

1.  Verify the order should not be placed without providing the mandatory fields Name and Phone number
2.  Test Quantity field should accept numeric values only
3.  Test Quantity field should not accept numbers more than 5 digits
3.  Test the cost field showing up the right value depending on the provided pizzaType and quantity
4.  Test the cost field should not be altered manually
5.  Verify the Name field not accepting more than 100 characters
6.  Verify the Email field not accepting more than 100 characters
7.  Verify the Phone field not accepting more than 100 digits
8.  Test the ability to select and unselect payment mode radio buttons
9.  Test the Place Order button
10. Test dialogue box message showing up the selected pizza type and correct cost.
11. Test dialogue box message when name and phone numbers are missing.
12. Test the dialogue box message when quantity value is not provided .
13. Test the Reset button, should clear all the entered values.

Defects/Issues Observed:
1. Pizza1/Pizza Type should be a mandatory field.
2. Even though pizza type selected as no toppings, in the form it is still allowing to select the toppings.
3. PizzaQuantity should be a mandatory field also should not accept characters.
4. Phone number should not accept characters.
5. No validations around phone number and email.
6. Order should not be placed without selecting the payment mode.
7. Only one payment mode should be selected. Currently, it is allowing to select both the payment options.
8. Reset button not clearing the pizza topping values.
9. After clicking place order button values are getting cleared.  

