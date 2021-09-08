Feature: Sign Up feature

  @create_account
  Scenario: firstly user is able to create an account for signing up in the application
    Given User opened the browser
    And User navigated to the application url and click on sign in button
    When User enter Email address as "icici5@gmail.com" to create an account and clicked on create new account button
    And User must be able to navigate to create an account page

  @form_filling
  Scenario: User have to fill personal information form
    Given User signed up
    When User have following list of information to send
      | Title      | Mrs.    |
      | First name | Yashuta |
      | Last name  | Nagdeve |
      | Password   | nandu2225  |
    And User select date of birth from dropdown
    And user able to click Sign up for our newsletter! and Receive special offers from our partners!
    And User have following details regarding address
      | Company                                      | Amdocs               |
      | Address                                      | Metro Manila,Amdocs  |
      | Address (Line 2)                             | 23F Ecotower         |
      | City                                         | Manila               |
      | Zip/Postal Code                              | 10000                |
      | Additional information                       | NA                   |
      | Home phone                                   | 7865743269           |
      | Mobile phone                                 | 8983207270           |
      | Assign an address alias for future reference | Magarpatta city,Pune |
    And User select state and country and click on Register button
    #Then Message display Welcome to your account.Here you can manage all of your personal information and orders







   

