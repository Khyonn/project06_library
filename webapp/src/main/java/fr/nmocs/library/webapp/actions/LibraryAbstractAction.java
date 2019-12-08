package fr.nmocs.library.webapp.actions;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.opensymphony.xwork2.ActionSupport;

import fr.nmocs.library.webapp.utils.UserUtils;
import fr.nmocs.library.webapp.ws.LibraryWebserviceException_Exception;
import fr.nmocs.library.webapp.ws.TokenService;
import fr.nmocs.library.webapp.ws.UserDTO;

@SuppressWarnings("serial")
public abstract class LibraryAbstractAction extends ActionSupport implements SessionAware {

	private static final String TOKEN_USER_KEY = "user";

	private static final String USER_TOKEN = "token";

	private static final String USER_CONNEXION_INFOS = "infos";

	private Map<String, Object> session;

	@Autowired
	private TokenService tokenService;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	// ===== USABLE IN TEMPLATES
	public boolean getIsUserConnected() {
		return session != null && session.containsKey(USER_TOKEN)
				&& JWT.decode((String) session.get(USER_TOKEN)).getExpiresAt().after(new Date());
	}

	public String getUserName() {
		if (session != null && session.containsKey(USER_TOKEN)) {
			return JWT.decode((String) session.get(USER_TOKEN)).getClaim(TOKEN_USER_KEY).asString();
		}
		return "";
	}

	// ===== GETTERS AND SETTERS AVAILABLE FOR CHILD ACTIONS

	protected Integer getUserId() {
		if (session != null && session.containsKey(USER_TOKEN)) {
			try {
				return Integer.parseInt(JWT.decode((String) session.get(USER_TOKEN)).getSubject());
			} catch (NumberFormatException e) {
				return UserUtils.WRONG_ID;
			}
		}
		return UserUtils.WRONG_ID;
	}

	protected void setUserToken(String token) {
		if (session != null) {
			session.put(USER_TOKEN, token);
		}
	}

	protected String getUserToken() {
		if (session != null) {
			return (String) session.get(USER_TOKEN);
		}
		return "";
	}

	protected void setUserInfos(UserDTO infos) {
		if (session != null) {
			session.put(USER_CONNEXION_INFOS, infos);
		}
	}

	protected void cleanSession() {
		if (session != null) {
			session.remove(USER_TOKEN);
			session.remove(USER_CONNEXION_INFOS);
		}
	}

	// ===== UTILS

	private UserDTO getUserInfos() {
		if (session != null) {
			return (UserDTO) session.get(USER_CONNEXION_INFOS);
		}
		return null;
	}

	protected void doReconnectIfNecessary() {
		UserDTO userInfos = getUserInfos();
		if (userInfos != null && !getIsUserConnected()) {
			try {
				setUserToken(tokenService.getLoginToken(userInfos.getEmail(), userInfos.getPassword()));
			} catch (LibraryWebserviceException_Exception e) {
				cleanSession();
				System.err.println(e.getFaultInfo().getMessage());
			}
		}
	}

}
