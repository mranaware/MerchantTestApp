
MerchantTestApp | Selenium Automation Project

This is an end-to-end Selenium Test Automation Framework for the Merchant Test Web Application.

It covers multiple test scenarios including:
- Login functionality (valid & invalid credentials)
- Forgot Password
- Page Load Performance
- Instagram Redirection
- Soft/Hard Assertions
- And more!

Project Structure:
------------------
MerchantTestApp/
├── src/
│   ├── main/java/         → Page classes, Utilities
│   └── test/java/         → Test classes
├── testng.xml             → TestNG Suite XML
├── pom.xml                → Maven config file
├── .gitignore             → Prevents uploading unnecessary files
└── README.txt             → Project instructions

Technologies Used:
------------------
- Java (17+)
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- GitHub

How to Run This Project:
------------------------
1. Prerequisites:
   - Java JDK 17+
   - Maven
   - Git
   - IntelliJ IDEA / Eclipse
   - Chrome browser

2. Clone the Repo:
   git clone https://github.com/mranaware/MerchantTestApp.git

3. Import the Maven Project:
   - Open IntelliJ or Eclipse
   - Select pom.xml to import dependencies

4. Run Tests Using TestNG:
   - Right-click on testng.xml > Run
   - OR use Maven: mvn clean test

Test Scenarios Automated:
--------------------------
- Valid Login
- Invalid Login (invalid email/password)
- Forgot Password flow
- Instagram link redirection
- Page load performance
- UI element validations
- Hard vs Soft Assertions (TestNG)
- Logging and Screenshots on failure

Design Patterns Used:
----------------------
- Page Object Model (POM)
- Reusable Utilities

Future Enhancements:
---------------------
- Integrate with Extent Reports
- Add Jenkins CI/CD
- Add Appium for mobile testing
- Support for Parallel Testing

Created By:
-----------
Mansi Ranaware
Selenium | TestNG | Java | Automation Engineer

Contact:
--------
GitHub: https://github.com/mranaware
