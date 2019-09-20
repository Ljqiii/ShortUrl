package com.ljqiii.shorturl.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StatusRepository {

    @Update("update status set v=#{id} where k='lastid'")
    int updateLastId(String id);

    @Select("select v from status where k='lastid'")
    String selectLastId();

}
