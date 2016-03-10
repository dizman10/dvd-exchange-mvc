package model.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * Бин для работы с дисками.
 * 
 * @author dizman
 *
 */

@Entity
public class Disc {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id_disc")
	@NotNull
	private int id;

	@NotNull
	private String title;
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_user")
	@NotNull
	private User user;

	public Disc() {
		this.id = 0;
		this.title = "";
		this.description = "";
	}
	
	public Disc(String title, String description, User user) {
		this.title = title;
		this.description = description;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desciption) {
		this.description = desciption;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
