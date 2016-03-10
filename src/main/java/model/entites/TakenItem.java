package model.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class TakenItem {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@NotNull
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_user")
	@NotNull
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_disc")
	@NotNull
	private Disc disc;

	@Column(name = "taken_time")
	@NotNull
	private Date takenTime;
	
	@Column(name = "return_time")
	private Date returnTime;

	public TakenItem() {
		this.user = null;
		this.disc = null;
		this.takenTime = null;
		this.returnTime = null;
	}
	
	public TakenItem(User user, Disc disc, Date takenTime, Date returnTime) {
		this.user = user;
		this.disc = disc;
		this.takenTime = takenTime;
		this.returnTime = returnTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Disc getDisc() {
		return disc;
	}

	public void setDisc(Disc disc) {
		this.disc = disc;
	}

	public Date getTakenTime() {
		return takenTime;
	}

	public void setTakenTime(Date takenTime) {
		this.takenTime = takenTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
}