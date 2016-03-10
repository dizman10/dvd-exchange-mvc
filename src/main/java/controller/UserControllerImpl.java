package controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.DAO.UserDAO;

/**
 * Контроллер для работы с пользователями.
 * Информация о методах:
 * @see controller.UserController
 * @author dizman
 *
 */
@Controller
public class UserControllerImpl implements UserController {
	private static final Logger log = Logger.getLogger(UserControllerImpl.class.getName());
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	public void setUserDAO(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(RedirectAttributes redir) {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/logincompleted")
	public ModelAndView login (@RequestParam(value = "name", required = true) String userName,
							@RequestParam(value = "password", required = true) String password,
							HttpSession session, RedirectAttributes redir) {
		ModelAndView model = new ModelAndView("completed");
		if ((userName == null) || (userName.isEmpty()) || (password == null) || password.isEmpty()) {
			model.setViewName("redirect:/login");
			redir.addFlashAttribute("message", "Ошибка входа в систему");
			redir.addFlashAttribute("details", "Введите логин и пароль");
			return model;
		}
		
		int id = userDao.login(userName, password);
		// Если id < 0, значит пользователя с таким именем/паролем не найдено.
		// В таком случае редиректем его на страницу входа.
		if (id < 0) {
			model.setViewName("redirect:/login");
			redir.addFlashAttribute("message", "Ошибка входа в систему");
			redir.addFlashAttribute("details", "Пользователя с таким именем/паролем не найдено");
			return model;
		}
		session.setAttribute("user", userName);
        session.setAttribute("idUser", id);
        model.addObject("title", "Вход выполнен");
        model.addObject("message", "Добро пожаловать, " + userName);
		return model;
	}
	
	@RequestMapping("/registration")
	public ModelAndView registation(RedirectAttributes redir) {
		return new ModelAndView("registration");
	}
	
	@RequestMapping("/registrationcompleted")
	public ModelAndView createNewUser(@RequestParam(value = "name", required = true) String userName,
									@RequestParam(value = "password", required = true) String password, 
									HttpSession session, RedirectAttributes redir) {
		ModelAndView model = new ModelAndView("completed");
		if ((userName == null) || (userName.isEmpty()) || (password == null) || password.isEmpty()) {
			model.setViewName("redirect:/registration");
			redir.addFlashAttribute("message", "Регистрация не удалась");
			redir.addFlashAttribute("details", "Логин и пароль не могут быть пустыми.");
			return model;
		}
		int id = userDao.createNewUser(userName, password);
		
		// Если id < 0, значит пользователя с таким именем/паролем не найдено.
		// В таком случае редиректем его на страницу входа.
		if (id < 0) {
			model.setViewName("redirect:/registration");
			redir.addFlashAttribute("message", "Регистрация не удалась");
			redir.addFlashAttribute("details", "Пользователь с таким именем уже существует");
			return model;
		}
		model.addObject("title", "Регистрация прошла успешно.");
		model.addObject("message", "Вы успешно зарегистрированы!");
		session.setAttribute("user", userName);
        session.setAttribute("idUser", id);
		return model;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView model = new ModelAndView("redirect:/login");
        session.invalidate();
        return model;
	}
}	
