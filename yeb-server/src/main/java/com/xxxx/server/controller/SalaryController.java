package com.xxxx.server.controller;


import com.xxxx.server.pojo.ResBean;
import com.xxxx.server.pojo.Salary;
import com.xxxx.server.service.SalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HC
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资账套")
    @PostMapping("/")
    public ResBean addSalary(@RequestBody Salary salary){
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)){
            return ResBean.success("添加成功！");
        }
        return ResBean.error("添加失败!");
    }

    @ApiOperation(value = "删除工资账套")
    @DeleteMapping("/{id}")
    public ResBean deleteSalary(@PathVariable Integer id){
        if (salaryService.removeById(id)){
            return ResBean.success("删除成功！");
        }
        return ResBean.error("删除失败！");
    }

    @ApiOperation(value = "更新工资账套")
    @PutMapping("/")
    public ResBean updateSalary(@RequestBody Salary salary){
        if (salaryService.updateById(salary)){
            return ResBean.success("更新成功!");
        }
        return ResBean.error("更新失败！");
    }
}
