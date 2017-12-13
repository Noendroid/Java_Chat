package tre;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MessageDao {

	public void addMessege(Message message) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			trns = session.beginTransaction();
			session.save(message);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void deleteMessege(int id) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			Message message = (Message) session.load(Message.class, new Integer(id));
			session.delete(message);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void updateMessege(Message message) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(message);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public List<Message> getAllMesseges() {
		List<Message> messages = new ArrayList<Message>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			messages = session.createQuery("from Message").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return messages;
	}

	public Message getMessegeById(int chatid) {
		Message message = null;
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			String queryString = "from Message where id = :messageId";
			Query query = session.createQuery(queryString);
			query.setInteger("messageId", chatid);
			message = (Message) query.uniqueResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return message;
	}

	public Message getPreviouseMessege(int messageId) {
		Message previouseMessage = null;
		if (messageId == RequestType.NONE) {
			List<Message> m = getAllMesseges();
			return m.get(m.size() - 1);
		}
		messageId--;
		while (messageId > -1) {
			previouseMessage = getMessegeById(messageId);
			if (previouseMessage != null) {
				return previouseMessage;
			}
			messageId--;
		}
		return null;
	}
}