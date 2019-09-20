package com.ljqiii.shorturl.repository;

import java.sql.Timestamp;

import com.ljqiii.shorturl.model.Url;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UrlRepository {

    @Delete("delete from shorturl where id=#{id}")
    int deleteById(@Param("id") String id);

    @Select("select id from shorturl where originurl=#{url}")
    String selectId(@Param("url") String originurl);

    @Select("select originurl from shorturl where id=#{id}")
    String selectOriginUrl(@Param("id") String id);

    @Insert("insert into shorturl(id,originurl)values(#{id},#{url})")
    int addShortUrl(@Param("id") String id, @Param("url") String Url);

    @Insert("insert into shorturl(id,originurl,expiretime)values(#{id},#{url},#{expiretime})")
    int addShortUrlWirhExpireTime(@Param("id") String id, @Param("url") String Url, @Param("expiretime") Timestamp expiretime);

    @Delete("delete from shorturl where originurl=#{url}")
    int deleteUrl(@Param("url") String url);

    @Select("select * from shorturl where id=#{id}")
    Url queryUrl(@Param("id") String id);
}
