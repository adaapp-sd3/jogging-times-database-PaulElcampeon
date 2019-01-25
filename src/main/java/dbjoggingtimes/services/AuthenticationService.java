package dbjoggingtimes.services;

import dbjoggingtimes.utility.SecurePasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private SecurePasswordUtility securePasswordUtility;

    public boolean authenticatePassword(String salt, String tempPassword, String securePassword) {
        return securePasswordUtility.generateSecurePassword(tempPassword, salt).equals(securePassword);
    }

    public boolean checkPasswordsMatch(String password1, String password2) {
        return password1.equals(password2);
    }
}
