package com.example.nla.mapper;

import com.example.nla.entity.Rec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Rec)表数据库访问层
 * @author makejava
 * @since 2022-04-05 21:21:25
 */
@Mapper
public interface RecMapper {

    /**
     * 通过ID查询单条数据
     * @param recId 主键
     * @return 实例对象
     */
    Rec queryById(Integer recId);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Rec> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     * @param rec 实例对象
     * @return 对象列表
     */
    List<Rec> queryAll(Rec rec);

    /**
     * 新增数据
     * @param rec 实例对象
     * @return 影响行数
     */
    int insert(Rec rec);

    /**
     * 修改数据
     * @param rec 实例对象
     * @return 影响行数
     */
    int update(Rec rec);

    /**
     * 通过主键删除数据
     * @param recId 主键
     * @return 影响行数
     */
    int deleteById(Integer recId);

}