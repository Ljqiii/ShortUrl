package com.ljqiii.shorturl;

import com.ljqiii.shorturl.model.Url;
import com.ljqiii.shorturl.repository.StatusRepository;
import com.ljqiii.shorturl.repository.UrlRepository;
import com.ljqiii.shorturl.service.UrlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import java.sql.Timestamp;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest

public class DataBaseTests {
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UrlService urlService;

    @Test
    public void testUpdateLastId() {
        statusRepository.updateLastId("10001");
    }

    @Test
    public void testInsertUrl() {
        urlService.createShortUrl("http://google.com", new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testSelectExitUrl() {
        Assert.assertEquals("10001", urlRepository.selectId("http://google.com"));
    }

    @Test
    public void testQueryById(){
        Url u=urlRepository. queryUrl("10001");
        Integer a=234;
    }



}
