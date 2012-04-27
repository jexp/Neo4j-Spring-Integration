package org.neo4j.integration.spring;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.kernel.configuration.Config;
import org.objectweb.jotm.Current;

/**
 * Open Web's JOTM specific test case.
 * 
 * @author Chris Gioran
 */
public class JOTMIntegrationTest extends BaseTMIntegrationTest {
	@Override
	protected String getConfigName() {
		return "classpath:spring-jotm-tx-test-context.xml";
	}

	@Test
	public void testLoadConfig() throws Exception {
		assertEquals(Current.class, tm.getTransactionManager().getClass());
		Map config = ((EmbeddedGraphDatabase) gds).getConfig()
				.getParams();
		assertEquals("spring-jta", config.get(Config.TXMANAGER_IMPLEMENTATION));

	}
}
