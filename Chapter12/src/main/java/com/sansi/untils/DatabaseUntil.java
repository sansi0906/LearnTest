package com.sansi.untils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DatabaseUntil {

    // 使用私有静态变量存储SqlSessionFactory单例
    private static SqlSessionFactory sqlSessionFactory;

    // 静态代码块，在类加载时初始化SqlSessionFactory
    static {
        try (Reader reader = Resources.getResourceAsReader("databaseConfig.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing SqlSessionFactory. Cause: " + e, e);
        }
    }

    // 提供一个方法来获取SqlSession，这里不再抛出IOException，因为它已经在静态代码块中处理了
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    // （可选）提供一个关闭SqlSession的方法，用于在不再需要时手动关闭SqlSession
    // 注意：通常推荐在业务逻辑结束时（例如在Service层的方法结束时）使用try-with-resources或手动关闭SqlSession
    public static void closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}