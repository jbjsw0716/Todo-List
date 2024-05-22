package com.keduit.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//테스트 3총사 RunWith, ContextConfiguration, Log4j

//해당 클래스나 메서드를 실행할 때 사용할 러너(runner)를 지정
@RunWith(SpringJUnit4ClassRunner.class)
//테스트 컨텍스트 설정을 지정하고 테스트 시에 필요한 애플리케이션 컨텍스트를 로드하는 데 사용
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j //클래스에 대한 로깅을 자동으로 추가하는데 사용
public class DatasourceTests {
	@Setter(onMethod_ = {@Autowired})
	private DataSource datasource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		try (Connection conn = datasource.getConnection()){
			log.info("----------" + conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMybatis() {
		try (SqlSession session = sqlSessionFactory.openSession();
				Connection conn = session.getConnection();) {
			log.info(session);
			log.info(conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
