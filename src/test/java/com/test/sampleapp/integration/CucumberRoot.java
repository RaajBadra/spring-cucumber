package com.test.sampleapp.integration;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootContextLoader;
import com.test.springkafkatest.SpringKafkaTestApplication;

import io.cucumber.spring.CucumberContextConfiguration;

import java.util.Collections;

/**
 * Class to use spring application context while running cucumber
 */
@CucumberContextConfiguration
@SpringBootTest(classes = SpringKafkaTestApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class CucumberRoot {

  // private static final Logger LOG = LoggerFactory.getLogger(CucumberRoot.class);

  /**
   * Need this method so the cucumber will recognize this class as glue and load spring context configuration
   */
  @Before
  public void setUp() {
	System.out.println("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
  }
}
