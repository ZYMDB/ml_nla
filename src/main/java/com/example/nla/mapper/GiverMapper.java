package com.example.nla.mapper;

import com.example.nla.entity.Giver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Giver)表数据库访问层
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Mapper
public interface GiverMapper {

    /**
     * 通过ID查询单条数据
     * @param givId 主键
     * @return 实例对象
     */
    Giver queryById(Integer givId);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Giver> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     * @param giver 实例对象
     * @return 对象列表
     */
    List<Giver> queryAll(Giver giver);

    /**
     * 新增数据
     * @param giver 实例对象
     * @return 影响行数
     */
    int insert(Giver giver);

    /**
     * 修改数据
     * @param giver 实例对象
     * @return 影响行数
     */
    int update(Giver giver);

    /**
     * 通过主键删除数据
     * @param givId 主键
     * @return 影响行数
     */
    int deleteById(Integer givId);

}