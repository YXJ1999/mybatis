package com.yang.mybatis.test;

import com.yang.mybatis.bean.Employee;
import com.yang.mybatis.dao.EmployeeMapper;
import com.yang.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    public SqlSession getSqlSession() throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
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
}
