package bean;

import model.entites.Disc;
import model.entites.User;

public class DiscUser {
	private Disc disc;
	private User user;
	
	public DiscUser(Disc disc, User user) {
		this.disc = disc;
		this.user = user;
	}
	
	public Disc getDisc() {
		return disc;
	}
	public void setDisc(Disc disc) {
		this.disc = disc;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
