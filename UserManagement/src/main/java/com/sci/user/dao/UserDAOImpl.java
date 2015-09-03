package com.sci.user.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sci.user.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		
	}
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> list() {
		LOGGER.info("In UserDAOImpl : list");
		return  (List<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();	
	}

	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		LOGGER.info("In UserDAOImpl : saveOrUpdate");
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void delete(int id) {
		LOGGER.info("In UserDAOImpl : delete");
		User userToDelete = new User();
		userToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	@Override
	@Transactional
	public User get(int id) {
		LOGGER.info("In UserDAOImpl : get");
		String hql = "from User where id= ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id);
		
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		
		return null;
	}

	@Override
	@Transactional
	public Boolean authenticate(User user) {
		LOGGER.info("In UserDAOImpl : authenticate");
		String hql = "from User where username=? and password=?";
		Boolean userExists = false;
		Query query = sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter(0, user.getUsername())
				.setParameter(1, user.getPassword());
		
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		if (listUser != null && !listUser.isEmpty()) {
			userExists = true;
		}
		
		return userExists;
		
	}

	@Override
	@Transactional
	public Boolean isUserExists(String userName) {
		LOGGER.info("In UserDAOImpl : authenticate");
		String hql = "from User where username=?";
		Boolean userExists = false;
		Query query = sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter(0, userName);
		
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		if (listUser != null && !listUser.isEmpty()) {
			userExists = true;
		}
		
		return userExists;
	}
}