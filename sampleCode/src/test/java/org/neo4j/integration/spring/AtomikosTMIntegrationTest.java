package org.neo4j.integration.spring;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.junit.Test;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.kernel.configuration.Config;

/**
 * Atomikos Transactions Essentials specific test case
 * 
 * @author Chris Gioran
 */
public class AtomikosTMIntegrationTest extends BaseTMIntegrationTest {

	@Test
	public void testLoadConfig() throws SystemException, NotSupportedException {
		assertEquals(com.atomikos.icatch.jta.UserTransactionManager.class, tm
				.getTransactionManager().getClass());
		Map<String, String> config = ((EmbeddedGraphDatabase) gds).getConfig()
				.getParams();
		assertEquals("spring-jta", config.get(Config.TXMANAGER_IMPLEMENTATION));
	}

	@Override
	protected String getConfigName() {
		return "classpath:spring-atomikos-tx-test-context.xml";
	}
}
