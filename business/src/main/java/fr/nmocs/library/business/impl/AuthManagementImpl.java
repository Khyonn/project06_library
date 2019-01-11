package fr.nmocs.library.business.impl;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.nmocs.library.business.AuthManagement;
import fr.nmocs.library.business.UserManagement;
import fr.nmocs.library.consumer.UserRepository;
import fr.nmocs.library.model.Admin;
import fr.nmocs.library.model.User;
import fr.nmocs.library.model.constants.UserStatus;
import fr.nmocs.library.model.error.ErrorCode;
import fr.nmocs.library.model.error.LibraryBusinessException;
import fr.nmocs.library.model.error.LibraryException;
import fr.nmocs.library.model.error.LibraryTechnicalException;

@Service
public class AuthManagementImpl implements AuthManagement {
    // ===== CONSTANTS
    private static final Integer HOUR_DURATION = 6; 
    private static final String SECRET_KEY = "y&6yxN,:D7Zu";
    private static final String ISSUER = "Library";
    private static final String USER_KEY = "user";
    private static final String ADMIN_KEY = "isAdmin";
    private static final Algorithm ALGO = Algorithm.HMAC256(SECRET_KEY);
    
    // ===== DEPENDENCIES
    
    @Autowired
    private UserManagement userMgmt;
    
    
    // ===== MAIN METHODS
    /**
     * 
     * @param userEmail
     * @param userPassword
     * @return
     * @throws LibraryException
     */
    @Override
    public String getToken(String userEmail, String userPassword) throws LibraryException {
        User user = userMgmt.findByEmail(userEmail);
        if (!StringUtils.equals(user.getPassword(), userPassword)) {
            throw new LibraryBusinessException(ErrorCode.PASSWORD_DOESNT_MATCH);
        }
        if (!StringUtils.equals(user.getStatus(), UserStatus.ACTIVE.getValue())) {
            throw new LibraryBusinessException(ErrorCode.USER_UNAUTHORIZED);
        }

        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (HOUR_DURATION * 3600 * 1000)))
                .withSubject(user.getId().toString())
                .withClaim(USER_KEY, user.getFirstName() + " " + user.getLastName())
                .withClaim(ADMIN_KEY, user instanceof Admin)
                .sign(ALGO);
    }
    
    /**
     * 
     * @param token
     * @throws LibraryBusinessException
     */
    @Override
    public void verifyToken(String token) throws LibraryBusinessException {
        try {            
            JWT.require(ALGO)
            .withIssuer(ISSUER)
            .build().verify(token);
        } catch (JWTVerificationException e) {
            throw new LibraryBusinessException(ErrorCode.TOKEN_NOT_VALID);
        }
    }
    
    /**
     * 
     * @param token
     * @return
     */
    @Override
    public User getUser(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return userMgmt.findById(Integer.parseInt(jwt.getSubject()));
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 
     * @param token
     * @return
     */
    @Override
    public Boolean isAdmin(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(ADMIN_KEY).asBoolean();
        } catch (Exception e) {
            return false;
        }
    }
    
}
