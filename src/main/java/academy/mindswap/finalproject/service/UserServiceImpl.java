package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.mapper.UserMapper;
import academy.mindswap.finalproject.model.classes.User;
import academy.mindswap.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.getReferenceById(userId);
        return userMapper.fromUserEntityToUserDto(user);
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = userMapper.fromUserCreateDtoToEntity(userCreateDto);
        user = userRepository.save(user);
        return userMapper.fromUserEntityToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
