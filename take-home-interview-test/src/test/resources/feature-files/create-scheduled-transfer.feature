@ComponentTest
Feature: Users are able to schedule transfers

  Scenario Outline: Scheduling with different valid scenarios is successful
    Given a user wants to schedule a transfer
    And the transfer has a to account number of <to_account_number>
    And the transfer has a from account number of <from_account_number>
    And the transfer has a transfer date of <transfer_date>
    And the transfer has an amount of <amount>
    And the transfer has a memo of <memo>
    When the user schedules the transfer
    Then the response is successful

    Examples:
      | to_account_number | from_account_number | transfer_date | amount | memo      |
      | 987654321         | 123456789           | 2022-05-05    | 10.00  | allowance |
      | 987654321         | 123456789           | 2023-01-01    | 25.00  | allowance |
      | 123456789         | 987654321           | 2022-06-01    | 100.00 | spending  |
      | 568410251         | 692010214           | 2022-10-31    | 75     | spending  |
      | 320147410         | 963201245           | 2022-11-09    | 75.0   | spending  |
      | 855410211         | 230125485           | 2024-01-01    | 50.00  |           |

  Scenario Outline: Invalid requests return a 400
    Given a user wants to schedule a transfer
    And the transfer has a to account number of <to_account_number>
    And the transfer has a from account number of <from_account_number>
    And the transfer has a transfer date of <transfer_date>
    And the transfer has an amount of <amount>
    And the transfer has a memo of <memo>
    When the user schedules the transfer
    Then the response is unsuccessful

    Examples:
      | to_account_number | from_account_number | transfer_date | amount   | memo                             |
      |                   | 123456789           | 2022-07-01    | 10.00    | No to account                    |
      | 987654321         |                     | 2022-08-15    | 25.00    | No from account                  |
      | 123456789         | 987654321           | 2023-01-01    | -100.00  | Negative amount                  |
      | 965885224         | 962014621           | 2022-09-01    | 99999999 | Too large of an amount           |
      | 320147410         | 963201245           | 2022-11-11    |          | No amount                        |
      | 320147410         | 963201245           | 2022-09-01    | 10.00    | This is a memo that is too long. |
      | 320147410         | 963201245           |               | 10.00    | No date                          |
      | 320147410         | 963201245           | 2019-01-01    | 10.00    | Date in past                     |