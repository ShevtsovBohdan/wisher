package com.springboot.persistence;

import com.springboot.domain.User;
import com.springboot.persistence.interfaces.ManageUser;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserTest extends DBUnitConfig{
        private ManageUser manageUser = new ManageUserImpl();


        @Before
        public void setUp() throws Exception {
            super.setUp();
            beforeData = new FlatXmlDataSetBuilder().build(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream("user-data.xml"));

            tester.setDataSet(beforeData);
            tester.onSetup();
        }

        public UserTest(String name) {
            super(name);
        }

        @Test
        public void testFindbyUserName() throws Exception {
            List<User> users = manageUser.listUsers();

            IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream("user-data.xml"));

            IDataSet actualData = tester.getConnection().createDataSet();
//            Assertion.assertEquals(expectedData, actualData);
            Assert.assertEquals(expectedData.getTable("users").getRowCount(), users.size());

        }

}
