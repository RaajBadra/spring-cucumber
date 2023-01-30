Feature:  Publish unprocessed message from primary topic to secondary topic

  Scenario: Processing of message fails after consumer consumes the message from primary topic
    Given A Message with correlation id="12345678"
    | correlationId   |
    | 12345678 |
    When unknown event with correlation id = "12345678" is published to secondary topic name="topic-2"
    Then Unprocessed message is published to dead letter topic="deadletter-topic" with the same correlation id="12345678" 