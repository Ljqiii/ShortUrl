package com.ljqiii.shorturl.repository;

import com.ljqiii.shorturl.model.NormalUser;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;


@Mapper
public interface UserRepository {
    @Select("select user.* from user,user_role,role where role.name='ROLE_admin' and user_role.roleid=role.id and user_role.userid=user.id")
    ArrayList<NormalUser> selectAllAdmin();

    @Select("select * from user where name=#{name}")
    NormalUser findByUsername(@Param("name") String username);

    @Select("select count(*) from user where name=#{name} and password=#{password}")
    int findByUserAndPasswd(@Param("name") String name, @Param("password") String password);

    @Select("select * from user where id=#{id}")
    NormalUser findById(@Param("id") int id);

    @Insert("insert into user(name,password)values(#{name},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Insert("insert into user(name,password)values(#{name},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(NormalUser normalUser);

    @Update("update user set description=#{description} where id=#{id}")
    void updateDescription(NormalUser user);

    @Delete("delete from user_role where userid=#{id}")
    int deleteRoleById(@Param("id") int id);

    @Delete("delete from user where id=#{id}")
    int deleteUserById(@Param("id") int id);

}
