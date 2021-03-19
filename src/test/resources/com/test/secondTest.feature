Feature: second feature test
  @get
  Scenario: get employee
    
    Given an employee exist in the database with id "2"
   When  user retrieves employee info by id
    Then the status code for get employee is 200
     And response includes the following employee info
     | status				| success	     |
	| data.employee_name 	| Garrett Winters|
    | data.id		        | 2         	 |
    | data.employee_age     | 63        	 |
 
  @post
Scenario: post employee
	Given an employee record is created with values
       | employee_name 	| Test Name1|
       | age		    | 41       |
       | salary		    | 320000   |
  
   @post
Scenario: post another employee
	Given an employee record is created with other values
	   | employee_name 	| Test Name2|
       | age		    | 50       |
       | salary		    | 500000   |
  
# @adhoctest
 # Scenario: retry failing test
 #   Given test fails because we want to test that way