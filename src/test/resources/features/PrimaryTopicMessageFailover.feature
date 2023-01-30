Feature:  Publish unprocessed message from primary topic to secondary topic

  Scenario: Processing of message fails after consumer consumes the message from primary topic
    Given A Message with following correlation id1
    | correlationId   |
    | 123456789 |
    When unknown event with correlation id = "123456789" is published to primary topic name="topic-1"
    Then Unprocessed message is published to secondary topic="test-2" with the same correlation id="123456789" 