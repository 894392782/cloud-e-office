package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 通过用户id查询菜单列表
     * @param id
     * @return
     */

}
