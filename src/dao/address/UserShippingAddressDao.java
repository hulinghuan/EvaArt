package dao.address;

import org.hibernate.HibernateException;

import dao.HibernateUtility;
import persistent.user.address.UserBillingAddress;

public class UserShippingAddressDao extends HibernateUtility{
	public Boolean addUserBillingAddress(UserBillingAddress userBillingAddress) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(userBillingAddress);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			return false;
		} finally {
			session.close();
		}
	}
}
