package com.young.zhang.service.impl;

import com.young.zhang.pojo.Category;
import com.young.zhang.mapper.CategoryMapper;
import com.young.zhang.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Young.Zhang
 * @since 2023-12-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
