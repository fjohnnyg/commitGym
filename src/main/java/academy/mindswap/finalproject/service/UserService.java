package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.converter.UserConverter;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.model.User;
import academy.mindswap.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    UserConverter userConverter = new UserConverter();
    public UserDto getUserById(Long userId) {
        User user = userRepository.getReferenceById(userId);
        return userConverter.fromUserEntityToUserDto(user);
    }

    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = userConverter.fromUserCreateDtoToEntity(userCreateDto);
        user = userRepository.save(user);
        return userConverter.fromUserEntityToUserDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
