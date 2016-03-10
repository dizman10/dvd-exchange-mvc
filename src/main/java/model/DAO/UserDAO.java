package model.DAO;

import javax.persistence.PersistenceContext;

/**
 * DAO для работы с пользователями.
 * @author dizman
 *
 */
public interface UserDAO {

	/**
	 * Вход пользователя
	 * @param userName - имя пользователя
	 * @param password - пароль
	 * @return id пользователя, если вход выполнен успешно,
	 * 			-1 - в противном случае
	 */
	public int login(String userName, String password);
	
	/**
	 * Cоздание нового пользователя
	 * @param userName - имя
	 * @param password - пароль
	 * @return - id пользователя, -1 - в случае, если пользователь с таким 
	 * 			именем уже существует
	 */
	public int createNewUser(String userName, String password);
}
