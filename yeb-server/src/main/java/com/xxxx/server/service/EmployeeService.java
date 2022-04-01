package com.xxxx.server.service;

import com.xxxx.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.ResBean;
import com.xxxx.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
public interface EmployeeService extends IService<Employee> {
    /**
     * 获取所有员工（分页）
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取工号
     * @return
     */
    ResBean maxWorkId();

    /**
     * 添加员工
     * @param employee
     * @return
     */
    ResBean addEmp(Employee employee);

    /**
     * 查询员工
     * @param id
     */
    List<Employee> getEmployee(Integer id);
}
