package com.example.nla.mapper;

import com.example.nla.entity.Temp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Temp)表数据库访问层
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Mapper
public interface TempMapper {

    /**
     * 通过ID查询单条数据
     * @param tempId 主键
     * @return 实例对象
     */
    Temp queryById(Integer tempId);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Temp> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     * @param temp 实例对象
     * @return 对象列表
     */
    List<Temp> queryAll(Temp temp);

    /**
     * 新增数据
     * @param temp 实例对象
     * @return 影响行数
     */
    int insert(Temp temp);

    /**
     * 修改数据
     * @param temp 实例对象
     * @return 影响行数
     */
    int update(Temp temp);

    /**
     * 通过主键删除数据
     * @param tempId 主键
     * @return 影响行数
     */
    int deleteById(Integer tempId);

}