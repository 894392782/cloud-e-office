package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户id获取菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);
    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();
}
