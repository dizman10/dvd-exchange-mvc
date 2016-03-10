package model.DAO;

import java.util.List;

import bean.DiscUser;
import model.entites.Disc;
import model.entites.User;

/**
 * Интерфейс для работы с дисками
 * 
 * @author dizman
 *
 */
public interface DiscDAO {
	/**
	 * Заведение нового диска
	 * 
	 * @param disc
	 *            - диск
	 */
	public void addNewDisc(String title, String description, int idUser);

	/**
	 * Список свободных дисков
	 * 
	 * @return - список
	 */
	public List<Disc> getAllFreeDiscs();

	/**
	 * Список дисков, взятых пользователем
	 * 
	 * @param idUser
	 *            - id пользователя
	 * @return - список дисков
	 */
	public List<Disc> getTakenDiscsByUser(int idUser);

	/**
	 * Cписок дисков, взятых у пользователя с указанием кто взял
	 * 
	 * @param idUser
	 *            - id пользователя
	 * @return - список, каждым элементом которого является бин DiscUser -
	 *         содержит двойку элементов: взятый диск и пользователя, который
	 *         этот диск взял
	 */
	public List<DiscUser> getTakenDiscsFromUser(int idUser);

	/**
	 * Взять диск
	 * 
	 * @param idUser
	 *            - id пользователя, который берет диск
	 * @param idDisc
	 *            - id диска, который берет пользователь
	 */
	public void takeDisc(int idUser, int idDisc);

	/**
	 * Вернуть диск
	 * 
	 * @param idUser
	 *            - id пользователя, который возвращает диск
	 * @param idDisc
	 *            - id диска, который возвращает пользователь
	 */
	public void returnDisc(int idUser, int idDisc);
}