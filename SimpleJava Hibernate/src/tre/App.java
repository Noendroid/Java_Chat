package tre;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {

	public static void main(String[] args) {
		ChatDao cDao = new ChatDao();
		// Add message
		Chat message = new Chat();
		message.setMessege("Hello World");
		Date currDate;
		try {
			currDate = new SimpleDateFormat("yyyy-MM-dd").parse("1986-01-02");
			message.setDate(currDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cDao.addMessege(message);
		List<Chat> chats = cDao.getAllMesseges();
		Chat givenMessege = cDao.getMessegeById(chats.get(chats.size() - 1).getId());
		givenMessege.setMessege("this messege was changed!");
		cDao.updateMessege(givenMessege);

		/*
		 * try { DbUtil.getConnection().close(); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */
	}
}