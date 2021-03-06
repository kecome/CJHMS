package com.homework; /**
 * Created by Administrator on 2017/3/30 0030.
 */

/**
 * 示例
 * @author
 * @create 2017-03-30 下午 16:03
 **/

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration    //该注解类似于spring配置文件
@MapperScan(basePackages="com.homework.mapper")
public class MybatisConfig {

    @Autowired
    private Environment env;

    /**
     * 创建数据源
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     */
    @Bean
    @Primary
    public DataSource getDataSource() throws Exception{
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        props.put("url", env.getProperty("spring.datasource.url"));
        props.put("username", env.getProperty("spring.datasource.username"));
        props.put("password", env.getProperty("spring.datasource.password"));
        props.put("filters", env.getProperty("spring.datasource.filters"));
        props.put("maxActive", env.getProperty("spring.datasource.maxActive"));
        props.put("initialSize", env.getProperty("spring.datasource.initialSize"));
        props.put("maxWait", env.getProperty("spring.datasource.maxWait"));
        props.put("minIdle", env.getProperty("spring.datasource.minIdle"));
        props.put("timeBetweenEvictionRunsMillis", env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis"));
        props.put("minEvictableIdleTimeMillis", env.getProperty("spring.datasource.minEvictableIdleTimeMillis"));
        props.put("validationQuery", env.getProperty("spring.datasource.validationQuery"));
        props.put("poolPreparedStatements", env.getProperty("spring.datasource.poolPreparedStatements"));
        props.put("maxOpenPreparedStatements", env.getProperty("spring.datasource.maxOpenPreparedStatements"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
     @Bean
     public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
         VFS.addImplClass(SpringBootVFS.class);

         SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
         fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
         //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
         fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));//指定基包
         fb.setTypeHandlersPackage(env.getProperty("mybatis.type-handlers-package"));
         fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));//指定xml文件位置
         return fb.getObject();
     }

}
