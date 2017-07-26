package com.springboot.dao;


import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageUser;
import com.springboot.interfaces.ManageWish;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class WishTest extends DBUnitConfig{
    private ManageWish manageWish = new ManageWishImpl();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("wish-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public WishTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception {
        long numberOfRows = manageWish.numberOfRows();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("wish-data.xml"));

        Assert.assertEquals(expectedData.getTable("wishes").getRowCount(), numberOfRows);
    }

    @Test
    public void testGetByUserID() throws Exception {
        long numberOfRows = manageWish.numberOfRows(1);


        Assert.assertEquals(2, numberOfRows);
    }

    @Test
    public void testSearchAll() throws Exception{
        List<Wish> wishes = manageWish.searchAllWishes("wish1");

        Assert.assertEquals(1, wishes.size());
    }

    @Test
    public void testSearch() throws Exception{
        List<Wish> wishes = manageWish.search(1,"wish1");

        Assert.assertEquals(1, wishes.size());
    }

    @Test
    public void testlistWishes() throws Exception{
        List<Wish> wishes = manageWish.listWishes(0, 1);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("wish-data-expected.xml"));

        Assert.assertEquals(expectedData.getTable("wishes"), wishes);
    }
}
