package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.mapper.UserMapper;
import academy.mindswap.finalproject.model.User;
import academy.mindswap.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    UserMapper userMapper;

    public UserDto getUserById(Long userId) {
        User user = userRepository.getReferenceById(userId);
        return userMapper.fromUserEntityToUserDto(user);
    }

    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = userMapper.fromUserCreateDtoToEntity(userCreateDto);
        user = userRepository.save(user);
        return userMapper.fromUserEntityToUserDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
