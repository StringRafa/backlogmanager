package com.panamby.backlogmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
@SpringBootTest
class BacklogmanagerApplicationTests {

	@Test
	void contextLoads() {
	}

}
