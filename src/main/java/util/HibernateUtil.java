package util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.entites.Disc;
import model.entites.TakenItem;
import model.entites.User;

/**
 * Класс для работы с ORM
 * 
 * @author dizman
 *
 */
public class HibernateUtil {
	
	private static final Logger log = Logger.getLogger(HibernateUtil.class.getName());
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		log.info("Получение hibernate.SessionFactory");
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(Disc.class);
			configuration.addAnnotatedClass(TakenItem.class);
			configuration.addAnnotatedClass(User.class);
			return configuration.buildSessionFactory(
					new StandardServiceRegistryBuilder().configure()
					.applySettings(configuration.getProperties()).build());
		} catch (Throwable ex) {
			log.error("Не удалось получить hibernate.SessionFactory");
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Возвращает фабрику для работы с Hibernate
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Возвращает текущую сессию
	 * @return
	 */
	public static Session getCurrentSession() {
		log.info("Получение hibernate.Session.");
		try {
			Session session = getSessionFactory().getCurrentSession();
			log.info("hibernate.Session получен.");
			return session;
		}
		catch (Exception exception) {
			log.error("Не удалось получить hibernate.Session и начать транзацию.");
			throw new ExceptionInInitializerError(exception);
		}
	}
	
	/**
	 * Начинает транзакцию для переданной сессии
	 * @param session
	 */
	public static void beginTransaсtion(Session session) {
		session.getTransaction().begin();
	}
	
	public static void commitTransaсtion(Session session) {
		session.getTransaction().commit();
	}
	
	public static void rollbackTransaсtion(Session session) {
		session.getTransaction().rollback();
	}
}
