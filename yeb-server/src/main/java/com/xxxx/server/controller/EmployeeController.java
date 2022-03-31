package com.xxxx.server.controller;


import com.xxxx.server.pojo.*;
import com.xxxx.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PoliticsStatusService politicsStatusService;
    @Autowired
    private JoblevelService joblevelService;
    @Autowired
    private NationService nationService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "获取所有员工（分页）")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee, LocalDate[] beginDateScope){
        return employeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsstatus")
    public List<PoliticsStatus> getAllPoliticStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getJoblevels(){
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDeps(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkId")
    public ResBean maxWorkId(){
        return employeeService.maxWorkId();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public ResBean addEmp(@RequestBody Employee employee){
        return employeeService.addEmp(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public ResBean updateEmp(@RequestBody Employee employee){
        if (employeeService.updateById(employee)){
            return ResBean.success("更新成功！");
        }
        return ResBean.error("更新失败！");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public ResBean deleteEmp(@PathVariable Integer id){
        if (employeeService.removeById(id)){
            return ResBean.success("删除成功！");
        }
        return ResBean.error("删除失败！");
    }

}
