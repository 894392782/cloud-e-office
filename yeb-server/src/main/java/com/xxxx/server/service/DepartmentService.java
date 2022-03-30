package com.xxxx.server.service;

import com.xxxx.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.ResBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
public interface DepartmentService extends IService<Department> {
    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param dep
     * @return
     */
    ResBean addDep(Department dep);

    /**
     * 删除部门
     * @param id
     * @return
     */
    ResBean deleteDep(Integer id);
}
