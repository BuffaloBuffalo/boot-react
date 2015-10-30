package react.api;

import java.io.Serializable;

/**
 * Representation of current logged in user. Stored in HTTP Session
 * 
 * @author jeffk
 *
 */
public class AuthenticatedUser implements Serializable {

	private static final long serialVersionUID = 635650630032240768L;
	private String username;
	private String token;
	private boolean isAuthenticated;

	public AuthenticatedUser() {
	}

	public AuthenticatedUser(String username, String token, boolean isAuthenticated) {
		this.username = username;
		this.token = token;
		this.isAuthenticated = isAuthenticated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
}