package com.example.nla.mapper;

import com.example.nla.entity.Env;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Env)表数据库访问层
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Mapper
public interface EnvMapper {

    /**
     * 通过ID查询单条数据
     * @param envId 主键
     * @return 实例对象
     */
    Env queryById(Integer envId);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Env> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     * @param env 实例对象
     * @return 对象列表
     */
    List<Env> queryAll(Env env);

    /**
     * 新增数据
     * @param env 实例对象
     * @return 影响行数
     */
    int insert(Env env);

    /**
     * 修改数据
     * @param env 实例对象
     * @return 影响行数
     */
    int update(Env env);

    /**
     * 通过主键删除数据
     * @param envId 主键
     * @return 影响行数
     */
    int deleteById(Integer envId);

}