package com.example.nla.mapper;

import com.example.nla.entity.Con;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Con)表数据库访问层
 * @author makejava
 * @since 2022-04-05 21:21:24
 */
@Mapper
public interface ConMapper {

    /**
     * 通过ID查询单条数据
     * @param conId 主键
     * @return 实例对象
     */
    Con queryById(Integer conId);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Con> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     * @param con 实例对象
     * @return 对象列表
     */
    List<Con> queryAll(Con con);

    /**
     * 新增数据
     * @param con 实例对象
     * @return 影响行数
     */
    int insert(Con con);

    /**
     * 修改数据
     * @param con 实例对象
     * @return 影响行数
     */
    int update(Con con);

    /**
     * 通过主键删除数据
     * @param conId 主键
     * @return 影响行数
     */
    int deleteById(Integer conId);

}