package com.young.zhang.service.impl;

import com.young.zhang.pojo.Article;
import com.young.zhang.mapper.ArticleMapper;
import com.young.zhang.service.ArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
