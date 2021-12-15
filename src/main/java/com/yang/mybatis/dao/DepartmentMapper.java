package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Department;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);
    public Department getDeptByIdPlus(Integer id);
}
