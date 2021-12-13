package com.yang.mybatis.test;

import com.yang.mybatis.bean.Employee;
import com.yang.mybatis.dao.EmployeeMapper;
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
    @Test
    public void testMybatisDao() throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //1、获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3、
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);
        sqlSession.close();
    }
}
