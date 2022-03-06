package com.xxxx.server.service.impl;

import com.xxxx.server.pojo.Admin;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
