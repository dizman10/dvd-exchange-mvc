package model.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import bean.DiscUser;
import model.entites.Disc;
import model.entites.TakenItem;
import model.entites.User;
import util.HibernateUtil;

/**
 * DAO для работы с дисками. 
 * Информация о методах:
 * @see model.DAO.DiscDAO
 * 
 * @author dizman
 *
 */
@Repository("discDao")
public class DiscDAOImpl implements DiscDAO {
	private static final Logger log = Logger.getLogger(DiscDAOImpl.class.getName());
	
	public void addNewDisc(String title, String description, int idUser) {
		log.info("Создание нового диска.");
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		
		User user = session.get(User.class, idUser);
		Disc disc = new Disc(title, description, user);
		session.save(disc);

		HibernateUtil.commitTransaсtion(session);
		log.info("Новый диск создан.");
	}

	public List<Disc> getAllFreeDiscs() {
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		
		log.info("Получение списка свободных дисков.");
		// Подзапросом получаем список взятых дисков.
		// Получаем только то, что не взяли (чего нет в подзапросе).
		String query = "from Disc as disc " + 
					   "where disc.id not in " + 
							"(select item.disc FROM TakenItem as item " + 
							"where item.returnTime is null) ";
		List<Disc> freeDiscs = session.createQuery(query).list();
		log.info("Список свободных дисков получен.");
		
		HibernateUtil.commitTransaсtion(session);
		return freeDiscs;
	}

	public List<Disc> getTakenDiscsByUser(int idUser) {
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		
		User user = session.get(User.class, idUser);
		log.info("Получение списка дисков, взятых пользователем c id = " + user.getId() + ".");
		// Получаем список дисков, которые были взяты у этого пользователя
		// и еще не возвращены
		String queryStr = "select disc from TakenItem as item " + 
						  "where item.user = :user and item.returnTime is null";
		Query query = session.createQuery(queryStr);
		query.setParameter("user", user);
		List<Disc> discsByUser = query.list();
		log.info("Список дисков, взятых пользователем c id = " + user.getId() + " получен.");
		
		HibernateUtil.commitTransaсtion(session);
		return discsByUser;
	}

	public List<DiscUser> getTakenDiscsFromUser(int idUser) {
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		
		User user = session.get(User.class, idUser);
		log.info("Получение списка взятых дисков у пользователя с id = " + user.getId() + ".");
		// Получаем список взятых дисков у заданного пользователя.
		// Дополнительно оборачиваем в список каждую пару элементов <Диск, Пользователь>,
		// где Диск - взятый диск, а Пользователь - тот, кто взял это диск.
		String queryStr = "select new list(disc, item.user) from TakenItem as item " + 
						  "join item.disc as disc " +
				          "where item.returnTime is null and disc.user = :user";
		Query query = session.createQuery(queryStr);
		query.setParameter("user", user);
		List<List<Object>> discsFromUserList = query.list();
		log.info("Список дисков взятых у пользователя c id = " + user.getId() + " получен.");
		
		log.info("Преобразование полученного списка к List<DiscUser>.");
		// Преобразовываем к более кошерному виду. Используем бин DiscUser
		List<DiscUser> discFromUser = new ArrayList<>();
		try {
			discsFromUserList.forEach(disc -> 
				discFromUser.add(new DiscUser((Disc)disc.get(0), (User)disc.get(1))));
		} catch (Exception exception) {
			log.error("Преобразование не удалось.");
			exception.printStackTrace();
		}
		log.info("Преобразование выполнено.");
		
		HibernateUtil.commitTransaсtion(session);
		return discFromUser;
	}

	@Override
	public void takeDisc(int idUser, int idDisc) {
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		
		log.info("Получение пользователем с id = " + idUser + " диска с id " + idDisc);
		User user = session.get(User.class, idUser);
		Disc disc = session.get(Disc.class, idDisc);
		// Третий параметр(время получения диска) - берем текущее
		// Четвертый параметр (время возвращения диска) - null, т.к. пользователь берет диск 
		TakenItem takenItem = new TakenItem(user, disc, new Date(), null);
		session.save(takenItem);
		log.info("Пользователь с id = " + idUser + " получил диск с id " + idDisc);
		
		HibernateUtil.commitTransaсtion(session);
	}

	@Override
	public void returnDisc(int idUser, int idDisc) {
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		
		log.info("Возврат диска с id = " + idUser + " пользователем с id = " + idUser);
		User user = session.get(User.class, idUser);
		Disc disc = session.get(Disc.class, idDisc);
		String queryStr = "update TakenItem set returnTime = :returnTime " +
					   "where user = :user and disc = :disc and returnTime is null";
		Query query = session.createQuery(queryStr);
		// Время возврата - берем текущее
		query.setParameter("returnTime", new Date());
		query.setParameter("user", user);
		query.setParameter("disc", disc);
		query.executeUpdate();
		
		HibernateUtil.commitTransaсtion(session);
	}
}
