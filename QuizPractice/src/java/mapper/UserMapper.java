package mapper;

import dto.RegisterUserDto;
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
        User userEntiry = new User();
        userEntiry.setFirstName(registerUserDto.getFirstName());
        userEntiry.setLastName(registerUserDto.getLastName());
        userEntiry.setEmail(registerUserDto.getEmail());
        userEntiry.setUsername(registerUserDto.getUsername());
        userEntiry.setPassword(registerUserDto.getPassword());
        return userEntiry;
    }
}