package com.yang.mybatis.dao;

import com.yang.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    public Employee getEmployeeById(Integer id);
    public void add(Employee employee);
    public void update(Employee employee);
    public void delete(Integer id);
    public Employee getEmpByIdAndLastName(@Param("id") String id, @Param("lastName")String lastName);
    public Employee getEmpByMap(Map map);
    public List<Employee> getEmployeeByLastNameLike(String lastName);
    //返回一条记录的map:key就是列名，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);
    //多条记录封装一个map:Map<Integer,Employee> :键是记录的主键，值是封装后的Java对象
    //告诉mybatis封装这个map的时候使用哪一个属性作为key
    @MapKey("id")
    public Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);
}
