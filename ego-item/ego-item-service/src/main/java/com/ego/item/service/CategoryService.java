package com.ego.item.service;

import com.ego.item.mapper.CategoryMapper;
import com.ego.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父类别id获取类别列表
     * @param pid 父类别id
     * @return
     */
    public List<Category> findListByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> result = categoryMapper.select(category);
        return result;
    }

    @Transactional
    public void save(Category category) {
        categoryMapper.insert(category);
    }

    public void updateNameById(Long id, String name) {
        categoryMapper.updateNameById(id, name);
    }

    public void deleteById(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
