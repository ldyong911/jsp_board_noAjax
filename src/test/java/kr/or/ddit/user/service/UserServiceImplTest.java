package kr.or.ddit.user.service;

import static org.junit.Assert.*;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImplTest {
	private SqlSession sqlSession;
	private IUserService userService;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		
		userService = new UserServiceImpl();
	}

	@Test
	public void testSelectUser() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVO userVO = userService.selectUser(userId);

		/***Then***/
		assertEquals(userId, userVO.getUserId());
	}

}