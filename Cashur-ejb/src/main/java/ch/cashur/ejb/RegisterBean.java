package ch.cashur.ejb;

import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.cashur.model.User;

@ManagedBean
@Stateless
public class RegisterBean implements RegisterBeanLocal {

	@PersistenceContext
	EntityManager em;
	
	private String firstName;
	private String surName;
	private String email;
	private String password;
	
	private User user = new User();

	/**
	 * Default constructor.
	 */
	public RegisterBean() {
		super();
	}

	@Override
	public String registerCustomer(String firstname, String surname, String email, String password) {

		user.setCurrency("CHF");
		user.setFirstname(firstname);
		user.setSurname(surname);
		user.setEmail(email);
		user.setPassword(password);

		em.persist(user);
		return "User successfully registered";
	}
	
	@Override
	public String registerCustomer(User user) {
		em.persist(user);
		return null;
	}
	
	@Override
	public boolean checkPassword(String password, String confirm) {
		// check if the 2 passwords are equal
		return false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
