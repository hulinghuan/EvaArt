package persistent.user.profile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import persistent.user.address.UserBillingAddress;
import persistent.user.address.UserShippingAddress;

public class UserProfile {
	private int profileId;
	private String profileLastName;
	private String profileFirstName;

	//private Set<UserBillingAddress> userBillingAddresses = new HashSet<UserBillingAddress>();
	//private Set<UserShippingAddress> userShippingAddresses = new HashSet<UserShippingAddress>();
	
	
	//main function for test only
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session;
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		/*UserProfile userProfile = new UserProfile();
		userProfile.setProfileFirstName("Linghuan");
		userProfile.setProfileLastName("Hu");
		//billing address and shipping address should be the individual and independent table
		UserAddress userAddress1 = new UserAddress();
		userAddress1.setBillingAddress("3600 Alma Rd");
		userAddress1.setShippingAddress("APT 2523");
		Set<UserAddress> addressSet = new HashSet<UserAddress>();
		addressSet.add(userAddress1);
		
		userProfile.setUserProfileAddress(addressSet);*/
		/*UserProfile userProfile;
		Query query = session.createQuery("from UserProfile where user_profile_id = 1");
		List userProfiles = query.list();
		userProfile = (UserProfile) userProfiles.get(0);
		UserAddress userAddress = new UserAddress();
		userAddress.setBillingAddress("gaoxingqu");
		userAddress.setShippingAddress("jiujian");
		userProfile.getUserProfileAddress().add(userAddress);
		session.saveOrUpdate(userProfile);
		session.getTransaction().commit();
		session.close();*/
		
		UserProfile userProfile;
		Query query = session.createQuery("from UserProfile where profileId = 1");
		List userProfiles = query.list();
		
		/*if(userProfiles.size() != 0) {
			userProfile = (UserProfile) userProfiles.get(0);
			UserBillingAddress userBillingAddress = new UserBillingAddress();
			userBillingAddress.setUser_billing_address("3600 Alma Rd, Apt 2523");
			UserShippingAddress userShippingAddress = new UserShippingAddress();
			userShippingAddress.setUser_shipping_address("3600 Alma Rd, Apt2523, Richardson");
			userProfile.getUserBillingAddresses().add(userBillingAddress);
			userProfile.getUserShippingAddresses().add(userShippingAddress);
			session.saveOrUpdate(userProfile);
			session.getTransaction().commit();
			session.close();
			userProfile = (UserProfile) userProfiles.get(0);
			for (UserBillingAddress userBillingAddress : userProfile.getUserBillingAddresses()) {
				System.out.println("BillingAddress :" + userBillingAddress.getUser_billing_address());
			}
			for (UserShippingAddress userShippingAddress : userProfile.getUserShippingAddresses()) {
				System.out.println("ShippingAddress :" + userShippingAddress.getUser_shipping_address());
			}
			
		}*/
	}
	
	

	public String getProfileLastName() {
		return profileLastName;
	}

	public void setProfileLastName(String profileLastName) {
		this.profileLastName = profileLastName;
	}

	public String getProfileFirstName() {
		return profileFirstName;
	}

	public void setProfileFirstName(String profileFirstName) {
		this.profileFirstName = profileFirstName;
	}


	
	/*public Set<UserBillingAddress> getUserBillingAddresses() {
		return userBillingAddresses;
	}



	public void setUserBillingAddresses(Set<UserBillingAddress> userBillingAddresses) {
		this.userBillingAddresses = userBillingAddresses;
	}



	public Set<UserShippingAddress> getUserShippingAddresses() {
		return userShippingAddresses;
	}



	public void setUserShippingAddresses(
			Set<UserShippingAddress> userShippingAddresses) {
		this.userShippingAddresses = userShippingAddresses;
	}*/



	public int getProfileId() {
		return profileId;
	}



	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
}
