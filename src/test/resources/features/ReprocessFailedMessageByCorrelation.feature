Feature:  Re-process failed message by correlation id

  Scenario: Failed message persisted to DB is reprocessed
    When a message with correlation id="123456" is read from DB
    Then re-process it by publishing it to topic="topic-1"
    
    