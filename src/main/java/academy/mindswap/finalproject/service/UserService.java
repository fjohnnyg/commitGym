package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;

public interface UserService {
    UserDto getUserById(Long userId);

    UserDto createUser(UserCreateDto userCreateDto);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long userId);

    UserDto setRoleClient(String username);

}
