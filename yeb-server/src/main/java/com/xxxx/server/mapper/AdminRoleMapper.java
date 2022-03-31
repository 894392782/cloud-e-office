package com.xxxx.server.mapper;

import com.xxxx.server.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.ResBean;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer updateAdminRole(Integer adminId, Integer[] rids);
}
