package managedbeans;

import dao.UserEJB;
import entities.Users;
import util.AuthenticationUtils;

import javax.annotation.security.DeclareRoles;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class LoginView implements Serializable {

	private static final long serialVersionUID = 3254181235309041386L;

	private static Logger log = Logger.getLogger(LoginView.class.getName());

	@Inject
	private UserEJB userEJB;

	private String email;
	private String password;

	private Users users;

	private String logInOrOut;

	public LoginView(){
		this.logInOrOut = "Log in";
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		this.logInOrOut = "Log out";
		try {
			request.login(email, password);
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
			return "signin";
		}

		Principal principal = request.getUserPrincipal();

		this.users = userEJB.findUserById(principal.getName());

		log.info("Authentication done for user: " + principal.getName());

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("Users", users);

		if (request.isUserInRole("users")) {
			return "/user/privatepage?faces-redirect=true";
		} else {
			return "signin";
		}
	}


	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		this.logInOrOut = "Log in";
		try {
			this.users = null;
			request.logout();
			// clear the session
			((HttpSession) context.getExternalContext().getSession(false)).invalidate();
		} catch (ServletException e) {
			log.log(Level.SEVERE, "Failed to logout user!", e);
		}

		return "/signin?faces-redirect=true";
	}

	public Users getAuthenticatedUser() {
		return users;
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

	public String getLogInOrOut() {
		return logInOrOut;
	}

	public void setLogInOrOut(String logInOrOut) {
		this.logInOrOut = logInOrOut;
	}


	public String logInOrLogOut(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			//If user is loggen in then logout, and if user is logged out, the login method.
			if (request.isUserInRole("users")) {
				return logout();
			} else {
				return "/signin?faces-redirect=true";
			}

	}
}
