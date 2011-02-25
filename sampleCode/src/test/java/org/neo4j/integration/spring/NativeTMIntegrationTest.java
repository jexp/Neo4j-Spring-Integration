package org.neo4j.integration.spring;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Map;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.xa.XAResource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.kernel.Config;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.springsource.transaction.core.TransactionService;

public class NativeTMIntegrationTest extends BaseTMIntegrationTest
{
	@Before
	public void recover()
	{
        getContext().getBean( "TransactionService",
                TransactionService.class ).recover(
                new LinkedList<XAResource>() );
	}

    @Override
    protected String getConfigName()
    {
        return "classpath:spring-native-tx-test-context.xml";
    }

    @Test
    public void testLoadConfig() throws SystemException, NotSupportedException
    {
        JtaTransactionManager tm = getContext().getBean(
                "JtaTransactionManager", JtaTransactionManager.class );
        Map<Object, Object> config = ( (EmbeddedGraphDatabase) gds ).getConfig().getParams();
        assertEquals( "spring-jta",
                config.get( Config.TXMANAGER_IMPLEMENTATION ) );
    }
}
