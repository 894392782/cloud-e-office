package com.xxxx.server.service;

import com.xxxx.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户id获取菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();
}
