package gnt.movies.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.entities.User;

public class TestUserBean {

	UserBean userBean;
	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		userBean=new UserBean();
		userBean.registerUserTest(new User(20, LocalDate.now(), "mail@mail.com", "John", "Maz", "12334", null,
				LocalDate.now(), "gmaz", null, null, null, null));
	}

}
