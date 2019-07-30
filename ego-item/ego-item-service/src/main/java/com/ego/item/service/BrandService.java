package com.ego.item.service;

import com.ego.common.pojo.PageResult;
import com.ego.item.mapper.BrandMapper;
import com.ego.item.pojo.Brand;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> page(Integer pageNo, Integer pageSize, String sortBy, Boolean descending, String key) {
        //1.分页
        PageHelper.startPage(pageNo, pageSize);
        //2.查询条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key))
        {
            criteria.andLike("name", "%" + key + "%")
                    .orEqualTo("letter",key.toUpperCase());
        }
        //3.排序
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(descending?"desc":"asc"));
        }


        Page<Brand> page = (Page<Brand>)brandMapper.selectByExample(example);
        return new PageResult<>(page.getTotal(),page.getResult());
    }
}
