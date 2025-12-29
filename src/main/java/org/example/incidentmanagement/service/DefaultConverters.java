package org.example.incidentmanagement.service;


@slf4j
@service
public class DefaultConverters {

    private final UserRepository userRepository;
    public DefaultConverters(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public static String convertBooleanToString(Boolean bool) {
        String resultText;
        if (bool == null) {
            return null;
        }else {
            resultText = bool ? "Y" : "N";
        }
        return resultText;
    }

    public static String returnUserFullName (Integer userID) {
        String userFullName = userRepository.findFullNameById(userID).orElse(null);
        logger.info("Called GetFullName on ID {}  Result: {}", userID, userFullName);
        return userFullName;
    }
}
