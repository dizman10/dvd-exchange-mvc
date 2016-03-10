package model.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * Бин для работы с пользователями
 * 
 * @author dizman
 *
 */
@Entity
public class User {
	/**
	 * Каждое следующее значение будет +1 к максимальному в таблице
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id_user")
	@NotNull
	private int id;

	@NotNull
	private String name;
	
	@NotNull
	private String password;

	@OneToMany(mappedBy = "user")
	private Set<Disc> discs;

	public User() {
		this.name = "";
		this.password = "";
		this.discs = new HashSet<Disc>();
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		this.discs = new HashSet<Disc>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Проверка пароля на правильность
	 * @param password - пароль, который необходимо проверить
	 * @return
	 */
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public Set<Disc> getDiscs() {
		return discs;
	}

	public void setDiscs(Set<Disc> discs) {
		this.discs = discs;
	}
}
