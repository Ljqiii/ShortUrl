package com.ljqiii.shorturl.service;

import com.ljqiii.shorturl.model.Url;
import com.ljqiii.shorturl.repository.StatusRepository;
import com.ljqiii.shorturl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    StatusRepository statusRepository;


    public String createShortUrl(String originUrl) {
        return createShortUrl(originUrl, null);
    }

    @Transactional
    public String createShortUrl(String originUrl, Timestamp date) {
        //如果网址不是以http开头，返回空
        if (!(originUrl.startsWith("http://") || originUrl.startsWith("https://"))) {
            return null;
        }

        //如果存在，返回id
        String id = urlRepository.selectId(originUrl);
        if (id != null) {
            return id;
        }

        //如果不存在，插入到数据库
        String lastId = statusRepository.selectLastId();
        String newId = Long.toString(Long.valueOf(lastId, 36) + 1, 36);

        if (date == null) urlRepository.addShortUrl(newId, originUrl);
        else urlRepository.addShortUrlWirhExpireTime(newId, originUrl, date);

        statusRepository.updateLastId(newId);

        return newId;
    }

    @CacheEvict(value = "searchfeed", key = "#root.args[0]")
    public int deleteShortUrl(String id) {
        return urlRepository.deleteById(id);
    }

    @Cacheable(value = "searchfeed", key = "#root.args[0]")
    public String queryOriginUrl(String id) {

        Url u = urlRepository.queryUrl(id);
        if(u==null){
            return null;
        }
        //如果没为设置过期时间，直接返回
        if (u.getExpiretime() == null) {
            return u.getOriginurl();
        } else {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if (u.getExpiretime().before(now)) {
                deleteShortUrl(id);//过期删除
                return null;
            }
            return u.getOriginurl();
        }
    }
}
