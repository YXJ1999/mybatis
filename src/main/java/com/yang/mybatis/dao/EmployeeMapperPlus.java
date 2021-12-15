package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);
    public Employee getEmpAndDept(Integer id);
    public Employee getEmpByIdStep(Integer id);
}
