package com.yang.mybatis.bean;

import java.util.List;

public class Department {
    /**
     * CREATE TABLE tbl_dept(
     * id INT(11) PRIMARY KEY,
     * dept_name VARCHAR(255)
     * )
     * ALTER TABLE tbl_employee ADD COLUMN d_id INT(11);
     * ALTER 	TABLE tbl_employee ADD CONSTRAINT fk_emp_dept
     * FOREIGN KEY (d_id) REFERENCES tbl_dept(id)
     */
    private Integer id;
    private String departmentName;
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Department() {
    }

    public Department(Integer id, String departmentName, List<Employee> employeeList) {
        this.id = id;
        this.departmentName = departmentName;
        this.employeeList = employeeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", employeeList=" + employeeList +
                '}';
    }
}
