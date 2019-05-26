package com.zwy.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * Created by zwy on 2019/5/26.
 * 每一个数据源对应一个DBConfig
 */
@Configuration
@EnableConfigurationProperties
public class DBConfig {
    /**
     * 1.配置数据源
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.ds_user")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 2.配置SqlSessionFactory和SqlSessionTemplate
     */
    @Bean
    @Primary
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource ds,
                                                   @Value("classpath:mybatis/mybatis-config.xml") Resource configLocation)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        sqlSessionFactoryBean.setConfigLocation(configLocation);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 3.如果在使用Mapper接口时不想都添加上Mapper注解
     * 那么需要配置MapperScannerConfigurer
     */
    @Bean
    @Primary
    public MapperScannerConfigurer userMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("userSqlSessionFactory");
        //此处指定了mapper的package就不需要在Mapper上@Mapper
        mapperScannerConfigurer.setBasePackage("com.zwy.dao.mapper");
        return mapperScannerConfigurer;
    }
}
