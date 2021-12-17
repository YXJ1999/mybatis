package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {
    //携带了那个字段就查询哪一个字段的值
    public List<Employee> getEmpsByConditionIf(Employee emp);
    public List<Employee> getEmpsByConditionTrim(Employee emp);
    public List<Employee> getEmpsByConditionChoose(Employee emp);
    public void updateEmp(Employee emp);
    public List<Employee> getEmpsByConditionForeach(List<Integer> ids);
    public void addEmps(@Param("emps") List<Employee> emps);
    public List<Employee> getEmpsTestInnerParameter(Employee employee);
}
