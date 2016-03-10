package util;

import java.util.Date;

import org.hibernate.Session;

import model.entites.Disc;
import model.entites.TakenItem;
import model.entites.User;

/**
 * Класс для создания схемы,
 * автоматического заведения данных.
 * @author dizman
 *
 */
public class GenerateUtil {
	
	public static void run() {
		createData();
	}
	
	/**
	 * Автоматическое заведение данных
	 */
	private static void createData() {
		Session session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaсtion(session);
		
		if (session.get(User.class, 1) != null) {
			return;
		}
		
		User dizman = new User("dizman", "acf07d69237005949e186227539589da");
		User Bob = new User("Bob", "2fc1c0beb992cd7096975cfebf9d5c3b");
		User John = new User("John", "61409aa1fd47d4a5332de23cbf59a36f");
		
		session.save(dizman);
		session.save(Bob);
		session.save(John);
		
		Disc callOfDuty = new Disc("Call Of Duty", "Call Of Duty", dizman);
		Disc callOfDuty2 = new Disc("Call Of Duty 2", "Call Of Duty 2", dizman);
		Disc callOfDuty3 = new Disc("Call Of Duty 3", "Call Of Duty 3", dizman);
		Disc callOfDuty4 = new Disc("Call Of Duty 4", "Call Of Duty 4", dizman);
		
		session.save(callOfDuty);
		session.save(callOfDuty2);
		session.save(callOfDuty3);
		session.save(callOfDuty4);
		
		Disc fallout = new Disc("Fallout", "Fallout", Bob);
		Disc fallout2 = new Disc("Fallout 2", "Fallout 2", Bob);
		Disc fallout3 = new Disc("Fallout 3", "Fallout 3", Bob);
		Disc fallout4 = new Disc("Fallout 4", "Fallout 4", Bob);
		
		session.save(fallout);
		session.save(fallout2);
		session.save(fallout3);
		session.save(fallout4);
		
		Disc hangover = new Disc("The hangover", "The hangover", John);
		Disc hangover2 = new Disc("The hangover 2", "The hangover 2", John);
		Disc hangover3 = new Disc("The hangover 3", "The hangover 3", John);
		
		session.save(hangover);
		session.save(hangover2);
		session.save(hangover3);
		
		TakenItem item1 = new TakenItem(dizman, hangover, new Date(), null);
		TakenItem item2 = new TakenItem(dizman, hangover2, new Date(), null);
		
		TakenItem item3 = new TakenItem(Bob, callOfDuty2, new Date(), null);
		TakenItem item4 = new TakenItem(Bob, callOfDuty4, new Date(), null);
		
		TakenItem item5 = new TakenItem(John, fallout, new Date(), null);
		TakenItem item6 = new TakenItem(John, fallout4, new Date(), null);
		
		session.save(item1);
		session.save(item2);
		session.save(item3);
		session.save(item4);
		session.save(item5);
		session.save(item6);
		
		HibernateUtil.commitTransaсtion(session);
	}
}