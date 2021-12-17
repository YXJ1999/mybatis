package com.yang.mybatis.test;

import com.yang.mybatis.bean.Department;
import com.yang.mybatis.bean.Employee;
import com.yang.mybatis.dao.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MybatisTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
    /**
     * 1.根据配置文件（全局配置文件）创建一个sqlSessionFactory对象
     * 有数据源一些运行环境信息
     * 2.sql映射文件：配置每一个sql,以及sql的封装规则等。
     * 3.将sql映射文件注册在全局配置文件中
     * 4.写代码
     *      1）、根据全局配置文件得到SqlSessionFactory
     *      2)、使用SqlSession工厂，获取sqlSession对象使用他来执行曾删改查
     *      3）、关闭sqlSession
     * @throws Exception
     */
    @Test
    public void testMybatis() throws Exception{
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //第一个参数Sql语句的唯一标识
        //第二个参数执行sql需要的参数
        Employee employee = sqlSession.selectOne("com.yang.mybatis.bean.Employee.selectBlog", 1);
        System.out.println(employee);
        sqlSession.close();
    }

    /**
     * 1、接口式编程
     * 原生：Dao ====>DaoImpl
     * mybatis Mapper ====>xxMapper.xml
     * 2、SqlSession代表和数据库的一次会话；用完必须关闭
     * 3、SqlSession和connection一样他都是非线成安全的，每次使用到要去获得新的对象
     * 不要放在成员变量中
     * 4、mapper接口没有实现类，但是mybatis会为接口生成一个代理对象
     * EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
     * 5、两个重要文件：
     * mybatis的全局配置文件：
     * sql映射文件：保存了每一个sql语句的映射信息
     * @throws IOException
     */
    @Test
    public void testMybatisDao() throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //1、获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3、获取接口的实现对象
        //会为接口自动创建一个代理对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);
        sqlSession.close();
    }
    @Test
    public void test01() throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
        System.out.println(mapper.getEmployeeById(1));
        sqlSession.close();
    }
    @Test
    public void test2 () throws Exception{
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        Employee dack = new Employee(null, "dack", "2@qq.com", "1");
//        mapper.add(dack);
//        System.out.println(dack);
//        mapper.update(new Employee(1,"tom","1@qq.com","0"));
//        mapper.delete(5);
        Employee tom = mapper.getEmpByIdAndLastName("1", "tom");
        System.out.println(tom);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test3()throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put("id",1);
//        hashMap.put("lastName","tom");
//        Employee empByMap = mapper.getEmpByMap(hashMap);
//        System.out.println(empByMap);
        List<Employee> employeeByLastNameLike = mapper.getEmployeeByLastNameLike("%o%");
        for (Employee e:employeeByLastNameLike) {System.out.println(e);}
        sqlSession.close();
    }
    @Test
    public void test4() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
        Map<Integer, Employee> map = mapper.getEmpByLastNameLikeReturnMap("tom");
        System.out.println(map);
        sqlSession.close();
    }
    @Test
    public void test5() throws Exception{
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
//        Employee empById = mapper.getEmpById(1);
//        System.out.println(empById);
//        Employee empAndDept = mapper.getEmpAndDept(1);
        Employee empByIdStep = mapper.getEmpByIdStep(1);
        System.out.println(empByIdStep);
        System.out.println(empByIdStep.getDept());
        sqlSession.close();
    }
    @Test
    public void test6() throws Exception{
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
//        Department dept = mapper.getDeptByIdPlus(1);
        Department dept = mapper.getDeptByIdStep(1);
        System.out.println(dept);
        System.out.println(dept.getEmployeeList());
        sqlSession.close();
    }
    @Test
    public void testDynamicSql () throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
//        List<Employee> emps = mapper.getEmpsByConditionIf(new Employee(1, "o", "1@qq.com", null));
//        List<Employee> emps = mapper.getEmpsByConditionChoose(new Employee(1, "o", "1@qq.com", null));
//        mapper.updateEmp(new Employee(1, "o", "1@qq.com", null));
//        sqlSession.commit();
//        for (Employee emp:emps){
//            System.out.println(emp);
//        }
        //查询的时候如果某些条件没带可能sql拼装会有问题
        //1、给where后面加上1=1，以后的条件都 and xxx
        //2、mybatis使用where标签会将where标签中的多出来的and或者or去掉
        //where只会去掉最开始的and或者or
//        List<Employee> empsByConditionForeach = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3, 4));
        List<Employee> emps=new ArrayList<>();
        Department department = new Department(1, "开发部", null);
        emps.add(new Employee(null, "o", "1@qq.com", "0",department));
        emps.add(new Employee(null, "o1", "2@qq.com", "2",department));
        emps.add(new Employee(null, "o2", "3@qq.com", "1",department));
        mapper.addEmps(emps);
        sqlSession.commit();
        sqlSession.close();
    }
}
