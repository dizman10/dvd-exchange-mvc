package model.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import model.entites.User;
import util.HibernateUtil;

/**
 * DAO для работы с пользователями.
 * Информация о методах:
 * @ see model.DAO.UserDAO
 * @author dizman
 *
 */
@Repository("userDao")
public class UserDAOImpl implements UserDAO {
	@PersistenceContext
	EntityManager em;
	
	public int login(String userName, String password) {
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		
		String queryStr = "from User where name=:name";
		Query query = session.createQuery(queryStr);
		query.setParameter("name", userName);
		List<User> users = query.list();
		HibernateUtil.commitTransaсtion(session);
		String passwordMd5Hash = getMd5Hash(password);
		
		if (users.isEmpty() || !users.get(0).checkPassword(passwordMd5Hash)) {
			return -1;
		}
		
		return users.get(0).getId();
	}
	
	public int createNewUser(String userName, String password) {
		String passwordMd5Hash = getMd5Hash(password);
		User user = new User(userName, passwordMd5Hash);
		
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		String queryStr = "from User where name = :name";
		Query query = session.createQuery(queryStr);
		query.setParameter("name", userName);
		List<User> existUser = query.list();
		if (existUser.size() > 0) {
			return -1;
		}
		session.save(user);
		HibernateUtil.commitTransaсtion(session);
		
		return user.getId();
	}
	
	/**
	 * Зашифровываем пароль (md5 хэш строки)
	 * @param password
	 * @return
	 */
	private String getMd5Hash(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return password;
		}
		md.update(password.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
}
