package com.spring5.service;

import com.spring5.anno.MyComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * Created by 康金 on 2019/2/18.
 */
@Service()
@MyComponent("MyComponent-TxService")
@Transactional(propagation = Propagation.NESTED)
public class TxService {
    @Autowired
    @Qualifier("MyComponent-simpleService")
    SimpleService simpleService;

    @Autowired
    TransactionTemplate transactionTemplate;
    @Autowired
    private DataSourceTransactionManager txManager;

    public void txSay() {
        try {
            simpleService.say1();
        } catch (UnexpectedRollbackException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 {@link TransactionTemplate}
     * 可以设置配置参数( {@link Transactional}的配置属性)
     * 可以在方法体中，自己实现事务的逻辑
     */
    public  void programmaticTx(){
        transactionTemplate.setTimeout(5);
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Nullable
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    int i = 1 / 0;
                    System.out.println("programmaticTx.doInTransaction");
                } catch (Exception e) {
                    if(status instanceof DefaultTransactionStatus)
                        try {
                            ((JdbcTransactionObjectSupport)((DefaultTransactionStatus) status).getTransaction()).getConnectionHolder().getConnection().rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                }
                return null;
            }
        });
    }

    /**
     * 使用 {@link DataSourceTransactionManager}
     *
     */
    public  void programmaticPlatformTransactionManager(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
// explicitly setting the transaction name is something that can be done only programmatically
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);
        try {
            // execute your business logic here
            System.out.println("programmaticPlatformTransactionManager");
        }
        catch (Exception ex) {
            txManager.rollback(status);
            throw ex;
        }
        txManager.commit(status);

    }
}
