package com.wyd.vbloglearn.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyd.vbloglearn.modules.mapper.TagsMapper;
import com.wyd.vbloglearn.modules.model.Tags;
import com.wyd.vbloglearn.modules.service.TagsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyd
 * @since 2022-08-31 22:09:07
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

}
