package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Employee;

public interface EmployeeMapper {
    public Employee getEmployeeById(Integer id);
}
