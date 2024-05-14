package mapper;

import dto.RegisterUserDto;
import java.sql.Date;
import model.User;

/**
 * The UserMapper class provides a utility method to convert a RegisterUserDto
 * object to a User entity. This is typically used to map data from a Data Transfer
 * Object (DTO) to an entity model for database operations.
 */
public class UserMapper {

    /**
     * Converts a RegisterUserDto object to a User entity.
     *
     * @param registerUserDto the RegisterUserDto object containing user registration data
     * @return a User entity with the corresponding data from the RegisterUserDto
     */
    public static User ConvertRegisterUserToUser(RegisterUserDto registerUserDto) {
        User userEntity = new User(); // Create a new User entity
        userEntity.setFirstName(registerUserDto.getFirstName()); // Set first name
        userEntity.setLastName(registerUserDto.getLastName()); // Set last name
        userEntity.setEmail(registerUserDto.getEmail()); // Set email
        userEntity.setPhoneNumber(registerUserDto.getPhoneNumber()); // Set phone number
        userEntity.setGender(registerUserDto.isGender()); // Set gender

        // Convert java.util.Date to java.sql.Date for database compatibility
        java.sql.Date sqlDate = new Date(registerUserDto.getDob().getTime());
        userEntity.setDob(sqlDate); // Set date of birth

        userEntity.setUsername(registerUserDto.getUsername()); // Set username
        userEntity.setPassword(registerUserDto.getPassword()); // Set password

        return userEntity; // Return the populated User entity
    }
}