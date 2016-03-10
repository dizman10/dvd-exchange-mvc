package controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface DiscController {
	/**
	 * Cписок свободных дисков
	 * @return
	 */
	public ModelAndView getAllFreeDiscs(HttpSession session);
	
	/**
	 * Получение диска
	 * @param idDisc - id диска
	 * @return
	 */
	@RequestMapping(value="takedisc")
	public ModelAndView takeDisc(@RequestParam(value = "id", required = true) Integer idDisc, 
								HttpSession session);
	
	/**
	 * Возвращение диска
	 * @param idDisc - id диска
	 * @return
	 */
	@RequestMapping(value="returndisc")
	public ModelAndView returnDisc(@RequestParam(value = "id", required = true) Integer idDisc, 
								HttpSession session);
	
	/**
	 * Добавление нового диска в систему
	 * @param title - название диска
	 * @param description - описание диска
	 * @return
	 */
	@RequestMapping("/addnewdisccompeted")
	public ModelAndView addNewDisc(@RequestParam(value = "title", required = true) String title,
									@RequestParam(value = "description", required = false) String description,
									HttpSession session, RedirectAttributes redir);
	
	/**
	 * Cписок дисков, которые взял пользователь
	 * @return
	 */
	@RequestMapping("/gettakendiscsbyuser")
	public ModelAndView getTakenDiscsByUser(HttpSession session);
	
	/**
	 * Cписок дисков, которые взяли у пользователя
	 * @return
	 */
	@RequestMapping("/gettakendiscsfromuser")
	public ModelAndView getTakenDiscsFromUser(HttpSession session);
}
