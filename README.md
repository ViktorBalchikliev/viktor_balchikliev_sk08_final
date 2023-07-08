Final project for Viktor Balchikliev
Skillo 08 - Automation

Project lead: Hjusein Tjurkmen
Supervisors: Karina Aneva, Dimitar Tarkalanov
Prepared by: Viktor Balchikliev

Testing focus: iSkillo platform
List of tasks:

1. Create a Java Maven test automation project based on TestNG and Selenium WebDriver
==> The current project "viktor_balchikliev_sk08_final" runs on Java Maven and is based on TestNG and Selenium WebDriver

2. Organise the project following Page Object Model design pattern and PageFactory
==> The current project is organised following POM design and PageFactory
    with the following division:
    - src/test/java/pages package containing all separate classes for the pages under test, including a BasePage class
    - src/test/java/tests package containing only the tests that will be run, divided into two separate classes to distinguish their focus
    - key methods and elements are first generated in their relevant page class and called when needed;
    - only elements that are used once are called within the testing classes

3. Automate a minimum of 5 test scenarios for the following platform: http://training.skillo-bg.com:4200/posts/all;
==> The project contains the following tests as described below:
   I. Guest Tests:
       //Designed to test platform functionality without logging in.
        1) Click on any user as a guest
        2) Like a post as a guest
        3) Dislike a post as a guest
           ==> the last test fails and saves a screenshot in src/resources/screenshots as requested.
   II. User Tests
       //Designed to test platform functionalities as a logged user
        1) Comment on another user's post
        2) Confirm pop-up appears when liking a post
        3) Confirm the 'Unfollow' button appears when you follow another user.

4. The project must include a testng.xml file
==> the testng.xml is present and contains the required information for both test classes

5. All tests should be run by the command 'mvn clean test' in Chrome Browser
==> All 6 tests can be run with a Maven clean test command.

6. When a test fails, a screenshot should be saved in a specific directory
==> As mentioned above, the third test is designed to fail and a screenshot is saved in src/resources/screenshots.
7. Create a public github repository which contains the code of the project.
==> the public github repo is available at: https://github.com/ViktorBalchikliev/viktor_balchikliev_sk08_final.git
8. The project must contain a README file that describes the entire project and the designed tests.
==> If all is well, you are reading the README at this very moment. :)