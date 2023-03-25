package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.exceptions.UserNotFoundException;
import academy.mindswap.finalproject.mapper.UserMapper;
import academy.mindswap.finalproject.model.entities.User;
import academy.mindswap.finalproject.repository.TokenRepository;
import academy.mindswap.finalproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
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

    @Override
    public UserDto setRoleClient(String userEmail) {
        Optional<User> optionalUser = userRepository.findByUsername(userEmail);
        return
        optionalUser.map(userMapper::fromUserEntityToUserDto).orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public UserDto getProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return userMapper.fromUserEntityToUserDto(user);
    }
}
