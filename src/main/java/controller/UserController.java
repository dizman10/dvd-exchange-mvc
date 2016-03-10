package controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Контроллер для работы с пользователями.
 * @author dizman
 *
 */
public interface UserController {
	/**
	 * Авторизация пользователя
	 * @param userName - имя пользователя
	 * @param password - пароль
	 * @param session - сессия
	 * @param redir - атрибуты, если случился редирект
	 * @return
	 */
	@RequestMapping("/logincompleted")
	public ModelAndView login(@RequestParam(value = "name", required = true) String userName,
							@RequestParam(value = "password", required = true) String password,
							HttpSession session, RedirectAttributes redir);
	
	/**
	 * Создание нового пользователя
	 * @param userName - имя пользователя
	 * @param password - пароль
	 * @param session - сессия
	 * @param redir - атрибуты, если случился редирект
	 * @return
	 */
	@RequestMapping("/registrationcompleted")
	public ModelAndView createNewUser(@RequestParam(value = "name", required = true) String userName,
									@RequestParam(value = "password", required = true) String password, 
									HttpSession session, RedirectAttributes redir);
	
	/**
	 * Выход пользователя
	 * @param session - сесси
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session);
	
}
