package com.ego.item.mapper;

import com.ego.item.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
public interface CategoryMapper extends Mapper<Category> {

    @Update("update tb_category set name = #{name} where id = #{id}")
    void updateNameById(@Param("id") Long id, @Param("name")String name);
}
