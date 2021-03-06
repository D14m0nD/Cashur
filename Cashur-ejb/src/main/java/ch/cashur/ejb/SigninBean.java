package ch.cashur.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import ch.cashur.model.User;

@Stateless
// @NamedQuery(name="JPAUserFunctions.findUser", query="SELECT f FROM f where
// email = f")
public class SigninBean implements SigninBeanLocal {

	@PersistenceContext
	EntityManager em;

	private String email;
	private String password;

	private FacesContext facesContext = FacesContext.getCurrentInstance();
    private HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
    
	public SigninBean() {

	}

	@Override
	public void signinCustomer(String email, String password) {
		System.out.println("SigninBean >> signinCustomer");
		List<User> u = new ArrayList<User>();
		u = em.createNamedQuery("User.findAll", User.class).getResultList();		
		
		for (User user : u) {
			if (user.getEmail().equals(email)) {
				System.out.println("LoginBean >> loginCustomer: User ist vorhanden");
				if (user.getPassword().equals(password)) {
					System.out.println("Passwort ist richtig");
					
			        session.setAttribute("user", user);
			        session.setAttribute("isLoggedIn", true);
			        
			        if (session.getAttribute("isLoggedIn") != null) {
			        	System.out.println("isLoggedIn = " + session.getAttribute("isLoggedIn"));
			        };
					return;
				} else {
					System.out.println("Passwort ist inkorrekt");
					return;
				}
			} else {
				System.out.println("LoginBean >> loginCustomer: User ist nicht vorhanden");
			}
		}
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