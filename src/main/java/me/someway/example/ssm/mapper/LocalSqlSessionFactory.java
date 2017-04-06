package me.someway.example.ssm.mapper;

import java.io.IOException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;

public class LocalSqlSessionFactory extends SqlSessionFactoryBean {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalSqlSessionFactory.class);
	
	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		try {
			return super.buildSqlSessionFactory();
		} catch (NestedIOException e) {
			logger.error("mapper.xml解析报错：",e);
			throw new NestedIOException("Failed to parse mapping resource: ", e);
		} finally {
			ErrorContext.instance().reset();
		}
	}
}
