package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.exceptions.UserNotFoundException;
import academy.mindswap.finalproject.mapper.UserMapper;
import academy.mindswap.finalproject.model.entities.User;
import academy.mindswap.finalproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.getReferenceById(userId)).thenReturn(user);
        when(userMapper.fromUserEntityToUserDto(user)).thenReturn(userDto);

        UserDto result = userServiceImpl.getUserById(userId);

        Assertions.assertEquals(userDto, result);
    }

    @Test
    void testCreateUser() {
        UserCreateDto userCreateDto = new UserCreateDto();
        User user = new User();
        UserDto userDto = new UserDto();

        when(userMapper.fromUserCreateDtoToEntity(userCreateDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.fromUserEntityToUserDto(user)).thenReturn(userDto);

        UserDto result = userServiceImpl.createUser(userCreateDto);

        Assertions.assertEquals(userDto, result);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        userServiceImpl.deleteUser(userId);

        Assertions.assertDoesNotThrow(() -> userRepository.deleteById(userId));
    }

    @Test
    void testSetRoleClient() {
        String userEmail = "test@test.com";
        User user = new User();
        UserDto userDto = new UserDto();

        when(userRepository.findByUsername(userEmail)).thenReturn(Optional.of(user));
        when(userMapper.fromUserEntityToUserDto(user)).thenReturn(userDto);

        UserDto result = userServiceImpl.setRoleClient(userEmail);

        Assertions.assertEquals(userDto, result);
    }

    @Test
    void testSetRoleClientThrowsUserNotFoundException() {
        String userEmail = "test@test.com";

        when(userRepository.findByUsername(userEmail)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userServiceImpl.setRoleClient(userEmail));
    }
}
