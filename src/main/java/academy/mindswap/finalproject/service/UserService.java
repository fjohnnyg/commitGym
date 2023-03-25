package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    UserDto createUser(UserCreateDto userCreateDto);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long userId);

    UserDto setRoleClient(String username);

    UserDto getProfile(String username);
}
