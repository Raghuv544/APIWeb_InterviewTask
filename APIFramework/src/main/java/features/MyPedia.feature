#Author: your.email@your.domain.com
@MyPedia
Feature: verifying the MyPedia application

  @MyPedia_tc1
  Scenario Outline: checking MyPedia application
    Given open browser and enter url
    Then checking language dropdown has at least 3 languages and verify
    When click on setup parent support
    Then filling the "<firstname>", "<lastname>", "<email address>", "<cpusername>", "<cppassword>", "<cpassword>"
    Then Close the Browser
    Examples: 
      | firstname | lastname | email address  | cpusername  | cppassword | cpassword |
      | abcd      | cdef     | abcd@gmail.com | abcdefgh123 | abcd1234   | abcd1234  |
      | efgh      | ghij     | efgh@gmail.com | efghij4566  | efgh1234   | efgh1234  |
