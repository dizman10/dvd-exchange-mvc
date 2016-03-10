package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bean.DiscUser;
import model.DAO.DiscDAO;
import model.entites.Disc;
import util.GenerateUtil;

/**
 * Котроллер для работы с дисками.
 * Описание методов:
 * @see controller.DiscController
 * @author dizman
 *
 */
@Controller
public class DiscControllerImpl implements DiscController {
	private static final Logger log = Logger.getLogger(DiscControllerImpl.class.getName());
	
	@Autowired
	@Qualifier("discDao")
	DiscDAO discDao;
	
	public void setDiscDao(DiscDAO discDao) {
		this.discDao = discDao;
	}

	@Override
	@RequestMapping("/freediscs")
	public ModelAndView getAllFreeDiscs(HttpSession session) {
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }
	    
	    ModelAndView model = new ModelAndView("freediscs");
	    List<Disc> freeDiscs;
	    try {
	    	log.info("Получение списка свободных дисков");
	    	freeDiscs = discDao.getAllFreeDiscs();
			// Удаление дисков, которые принадлежат этому пользователю 
			// Для того чтобы пользователю не показывались его диски
			freeDiscs.removeIf(disc -> idUser.equals(disc.getUser().getId()));
			log.info("Список свободных дисков получен");
	    }
	    catch (Exception exception) {
	    	log.error("Не удалось получить список свободных дисков");
	    	exception.printStackTrace();
	    	model.addObject("message", "Не удалось получить список дисков");
	    	model.addObject("desciption", "Не удалось получить список дисков, обратитесь к администратору");
	    	return model;
		}
	    
	    model.addObject("freeDiscs", freeDiscs);
		return model;
	}
	
	@Override
	@RequestMapping(value="takedisc")
	public ModelAndView takeDisc(@RequestParam(value = "id", required = true) Integer idDisc, 
									HttpSession session) {
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }
		
	    ModelAndView model = new ModelAndView("completed");
	    try {
	    	log.info("Взятие диска с id = " + idDisc + " пользователем c id = " + idUser);
		    discDao.takeDisc(idUser, idDisc);
		    log.info("Успешно выполнено взятие диска с id = " + idDisc + " пользователем c id = " + idUser);
	    }
	    catch (Exception exception) {
	    	log.error("Не удалось взятие диска с id = " + idDisc + " пользователем c id = " + idUser);
	    	exception.printStackTrace();
	    	model.addObject("title", "Не удалось получить диск");
	    	model.addObject("message", "Не удалось получить диск. Обратитесь к администратору");
	    }
	    model.addObject("title", "Диск получен");
		model.addObject("message", "Диск успешно взят!");
		return model;
	}
	
	@RequestMapping(value="/returndisc")
	public ModelAndView returnDisc(HttpSession session) {
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }
		
	    ModelAndView model = new ModelAndView("returndisc");
	    try {
	    	log.info("Получение списка дисков, которые взял пользователь с id = " + idUser);
	    	List<Disc> takenDiscs = discDao.getTakenDiscsByUser(idUser);
	    	model.addObject("takenDiscs", takenDiscs);
	    	log.info("Получен список дисков, которые взял пользователь с id = " + idUser);
	    }
	    catch (Exception exception) {
	    	log.error("Не удалось получить список дисков, которые взял пользователь с id = " + idUser);
	    	exception.printStackTrace();
	    	model.addObject("title", "Не удалось вернуть диск");
	    	model.addObject("message", "Не удалось вернуть диск. Обратитесь к администратору");
	    }
		
	    model.addObject("title", "Диск получен");
		model.addObject("message", "Диск успешно взят!");
		
		return model;
	}
	
	@Override
	@RequestMapping(value="/returndisccompleted")
	public ModelAndView returnDisc(@RequestParam(value = "id", required = true) Integer idDisc, 
									HttpSession session) {
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }

	    ModelAndView model = new ModelAndView("completed");
	    try {
	    	log.info("Возвращение диска с id = " + idDisc + " пользователем с id = " + idUser);
	    	discDao.returnDisc(idUser, idDisc);
	    	log.info("Успешно выполнено возвращение диска с id = " + idDisc + " пользователем с id = " + idUser);
	    }
	    catch (Exception exception) {
	    	log.error("Не удалось выполнить возвращение диска с id = " + idDisc + " пользователем с id = " + idUser);
	    	exception.printStackTrace();
	    	model.addObject("title", "Не удалось вернуть диск");
	    	model.addObject("message", "Не удалось вернуть диск. Обратитесь к администратору.");
	    }
	    model.addObject("title", "Диск был успешно возвращен");
	    model.addObject("message", "Диск был успешно возвращен!");
	    
		return model;
	}
	
	@RequestMapping("/addnewdisc")
	public ModelAndView addNewDisc(HttpSession session, RedirectAttributes redir) {
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }
		return new ModelAndView("addnewdisc");
	}
	
	@RequestMapping(value = {"/", "index" })
	public ModelAndView index(HttpSession session) {
		GenerateUtil.run();
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }
	    ModelAndView model = new ModelAndView("index");
	    model.addObject("user", session.getAttribute("user"));
		return model;
	}
	
	@Override
	@RequestMapping("/addnewdisccompeted")
	public ModelAndView addNewDisc(@RequestParam(value = "title", required = true) String title,
									@RequestParam(value = "description", required = false) String description,
									HttpSession session, RedirectAttributes redir) {
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }
		
		ModelAndView model = new ModelAndView("completed");
		if ((title == null) || (title.length() < 3)) {
			model.setViewName("redirect:/addnewdisc");
			redir.addFlashAttribute("message", "Попытка создания диска не удалась.");
			redir.addFlashAttribute("details", "Название диска не может быть пустым или короче 3 символов.");
			return model;
		}
		
		try {
			log.info("Добавление нового диска с названием " + title + " пользователем с id = " + idUser);
			discDao.addNewDisc(title, description, idUser);
			log.info("Добавлен новый диск с названием " + title + " пользователем с id = " + idUser);
			model.addObject("title", "Диск добавлен");
			model.addObject("message", "Диск с названием " + title + " успешно добавлен!");
		}
		catch (Exception exception) {
			log.error("Не удалось добавление нового диска с названием " + title + " пользователем с id = " + idUser);
			exception.printStackTrace();
			model.addObject("title", "Добавление диска не удалось");
			model.addObject("message", "Добавление диска не удалось. Обратитесь к администратору");
		}
		
		return model;
	}

	@Override
	@RequestMapping("/takendiscsbyuser")
	public ModelAndView getTakenDiscsByUser(HttpSession session) {
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }
	    
		ModelAndView model = new ModelAndView("takendiscsbyuser");
		try {
	    	log.info("Получение списка дисков, которые взял пользователь с id = " + idUser);
	    	List<Disc> takenDiscs = discDao.getTakenDiscsByUser(idUser);
			model.addObject("takenDiscs", takenDiscs);
	    	log.info("Получен список дисков, которые взял пользователь с id = " + idUser);
	    }
	    catch (Exception exception) {
	    	log.error("Не удалось получить список дисков, которые взял пользователь с id = " + idUser);
	    	exception.printStackTrace();
	    	model.addObject("message", "Не удалось получить список дисков, которые вы взяли. " + 
	    								"Обратитесь к администратору.");
	    }
		
		return model;
	}

	@Override
	@RequestMapping("/takendiscsfromuser")
	public ModelAndView getTakenDiscsFromUser(HttpSession session) {
		Integer idUser = checkUserAuthorization(session);
	    if (idUser == null) {
	    	return goToLoginPage();
	    }
	    
		ModelAndView model = new ModelAndView("takendiscsfromuser");
		try {
	    	log.info("Получение списка дисков, которые взяли у пользователя с id = " + idUser);
	    	List<DiscUser> takenDiscs = discDao.getTakenDiscsFromUser(idUser);
			model.addObject("takenDiscs", takenDiscs);
	    	log.info("Получен список дисков, которые взяли у пользователя с id = " + idUser);
	    }
	    catch (Exception exception) {
	    	log.error("Не удалось получить список дисков, которые взяли у пользователя с id = " + idUser);
	    	exception.printStackTrace();
	    	model.addObject("message", "Не удалось получить список дисков, которые взяли у вас. " + 
					"Обратитесь к администратору.");
	    }
		
		return model;
	}
	
	private Integer checkUserAuthorization(HttpSession session) {
		log.info("Проверка авторизации пользователя.");
		return (Integer) session.getAttribute("idUser");
	}
	
	private ModelAndView goToLoginPage() {
		log.info("Пользователь не авторизован. Перенаправление на страницу авторизации.");
		return new ModelAndView("redirect:/login");
	}
}
