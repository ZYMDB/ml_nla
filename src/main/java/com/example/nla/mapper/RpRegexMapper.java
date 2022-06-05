package com.example.nla.mapper;

import com.example.nla.entity.RpRegex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RpRegex)表数据库访问层
 * @author makejava
 * @since 2022-04-24 17:11:41
 */
@Mapper
public interface RpRegexMapper {

    /**
     * 通过ID查询单条数据
     * @param modeCode 主键
     * @return 实例对象
     */
    RpRegex queryById(Integer modeCode);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RpRegex> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     * @param rpRegex 实例对象
     * @return 对象列表
     */
    List<RpRegex> queryAll(RpRegex rpRegex);

    /**
     * 新增数据
     * @param rpRegex 实例对象
     * @return 影响行数
     */
    int insert(RpRegex rpRegex);

    /**
     * 修改数据
     * @param rpRegex 实例对象
     * @return 影响行数
     */
    int update(RpRegex rpRegex);

    /**
     * 通过主键删除数据
     * @param modeCode 主键
     * @return 影响行数
     */
    int deleteById(Integer modeCode);

    /**
     * 获取数据表的总记录数
     * @return
     */
    Integer getLength();
}