package com.example.nla.mapper;

import com.example.nla.entity.Rp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Rp)表数据库访问层
 * @author makejava
 * @since 2022-04-24 17:19:57
 */
@Mapper
public interface RpMapper {

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Rp queryById(Integer id);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Rp> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     * @param rp 实例对象
     * @return 对象列表
     */
    List<Rp> queryAll(Rp rp);

    /**
     * 新增数据
     * @param rp 实例对象
     * @return 影响行数
     */
    int insert(Rp rp);

    /**
     * 修改数据
     * @param rp 实例对象
     * @return 影响行数
     */
    int update(Rp rp);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询所有记录
     * @return
     */
    List<Rp> queryAll2();
}