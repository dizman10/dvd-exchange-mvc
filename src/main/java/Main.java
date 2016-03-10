import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		/*
		 * Session session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction();
		 * 
		 * /*User user = new User(); user.setName("dizman");
		 * user.setPhone("89173876644");
		 */

		/*
		 * Disc disc = new Disc(); disc.setTitle("Fast"); disc.setUser(user);
		 */

		/*
		 * Disc disc = session.get(Disc.class, 1);
		 * 
		 * TakenItem takenItem = new TakenItem(user, disc, new Date());
		 * session.save(takenItem);
		 */

		/*
		 * TakenItem takenItem = session.get(TakenItem.class, 1);
		 * System.out.println(takenItem.getUser().getName()) User user =
		 * session.get(User.class, 1); DiscDAO disc = new DiscDAOImpl();
		 * session.getTransaction().commit();
		 **/

		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			String url = "jdbc:hsqldb:mem:disc";
			connection = DriverManager.getConnection(url, "SA", "");
			connection.setAutoCommit(false);

			statement = connection.createStatement();
			System.out.print("fddf");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				} // nothing we can do
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				} // nothing we can do
			}
		}
	}

}
