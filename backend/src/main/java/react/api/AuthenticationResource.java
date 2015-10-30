package react.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/session")
class AuthenticationResource {
	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(method = RequestMethod.POST)
	public Object login(@RequestBody Map<String, String> credentials, HttpSession httpSession) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(credentials.get("username"),
				credentials.get("password"));
		authenticationManager.authenticate(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		httpSession.setAttribute("user", new AuthenticatedUser(credentials.get("username"), httpSession.getId(), true));
		return session(httpSession);
	}

	@RequestMapping(method = RequestMethod.GET)
	public AuthenticatedUser session(HttpSession session) {
		return (AuthenticatedUser) session.getAttribute("user");
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void logout(HttpSession session) {
		session.invalidate();
	}
}
