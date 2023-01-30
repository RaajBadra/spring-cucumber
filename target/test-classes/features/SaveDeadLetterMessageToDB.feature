Feature:  Save deadletter queue messages to DB

  Scenario: A message is published to deadletter queue topic
    When a message with correlation id="1234567" is published to deadletter topic
    Then The message with id="1234567" should be persisted to DB
    
    