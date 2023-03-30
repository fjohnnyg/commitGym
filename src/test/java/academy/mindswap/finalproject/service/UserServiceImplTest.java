package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.exceptions.*;
import academy.mindswap.finalproject.mapper.*;
import academy.mindswap.finalproject.model.entities.*;
import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.model.enums.Specializations;
import academy.mindswap.finalproject.repository.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private FitnessTestRepository fitnessTestRepository;
    @Mock
    private PersonalTrainerRepository personalTrainerRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private FitnessTestMapper fitnessTestMapper;
    @Mock
    private PersonalTrainerMapper personalTrainerMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private DailyPlanRepository dailyPlanRepository;
    @Mock
    private DailyPlanMapper dailyPlanMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProfileReturnsCorrectUserDto() {
        String username = "john_doe";
        User user = new User();
        user.setUsername(username);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password123");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("johndoe@example.com");
        userDto.setPassword("password123");

        when(userMapper.fromUserEntityToUserDto(user)).thenReturn(userDto);

        UserDto expected = userService.getProfile(username);
        assertEquals(expected, userDto);
        verify(userRepository, times(1)).findByUsername(username);
        verify(userMapper, times(1)).fromUserEntityToUserDto(user);
    }

    @Test
    void testGetProfileThrowsUserNotFoundException() {
        String username = "john_doe";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getProfile(username));
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testUpdateUserProfileThrowsUserNotFoundException() {
        String username = "john_doe";
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUserProfile(username, userDto));
        verify(userRepository, times(1)).findByUsername(username);
        verifyNoMoreInteractions(passwordEncoder);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userMapper);
    }

    @Test
    void testScheduleMyFitnessTestThrowsUserNotFoundException() {
        String username = "johndoe";
        FitnessTestCreateDto fitnessTestCreateDto = new FitnessTestCreateDto();
        fitnessTestCreateDto.setPersonalTrainerUsername("janesmith");
        fitnessTestCreateDto.setDate(LocalDate.now().plusDays(7));
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.scheduleMyFitnessTest(username, fitnessTestCreateDto));
        verify(userRepository, times(1)).findByUsername(username);
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(personalTrainerRepository);
        verifyNoMoreInteractions(fitnessTestRepository);
        verifyNoMoreInteractions(fitnessTestMapper);
    }

    @Test
    void testScheduleMyFitnessTestThrowsInvalidDate() {
        String username = "johndoe";
        FitnessTestCreateDto fitnessTestCreateDto = new FitnessTestCreateDto();
        fitnessTestCreateDto.setPersonalTrainerUsername("janesmith");
        fitnessTestCreateDto.setDate(LocalDate.now().minusDays(7));
        User clientUser = new User();
        clientUser.setUsername(username);
        User personalTrainerUser = new User();
        personalTrainerUser.setUsername(fitnessTestCreateDto.getPersonalTrainerUsername());
        PersonalTrainer personalTrainer = new PersonalTrainer();
        personalTrainer.setUser(personalTrainerUser);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(clientUser));
        when(userRepository.findByUsername(fitnessTestCreateDto.getPersonalTrainerUsername())).thenReturn(Optional.of(personalTrainerUser));
        assertThrows(InvalidDate.class, () -> userService.scheduleMyFitnessTest(username, fitnessTestCreateDto));
        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).findByUsername(fitnessTestCreateDto.getPersonalTrainerUsername());
        verifyNoMoreInteractions(personalTrainerRepository);
        verifyNoMoreInteractions(fitnessTestRepository);
        verifyNoMoreInteractions(fitnessTestMapper);
    }

    @Test
    void testInactivateAccount() {
        String username = "john_doe";
        User user = new User();
        user.setUsername(username);
        user.setRoles(new HashSet<>(Arrays.asList(Role.CLIENT)));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        userService.inactivateAccount(username);
        Set expectedRoles = new HashSet<>();
        expectedRoles.add(Role.INACTIVE);
        assertEquals(expectedRoles, user.getRoles());
        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testInactivateAccountThrowsUserNotFoundException() {
        String username = "john_doe";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.inactivateAccount(username));
        verify(userRepository, times(1)).findByUsername(username);
        verifyNoMoreInteractions(userRepository);
    }
    @Test
    void testGetLatestFitnessTestThrowsUserNotFoundException() {
        String username = "john_doe";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getLatestFitnessTest(username));
        verify(userRepository, times(1)).findByUsername(username);
        verifyNoMoreInteractions(fitnessTestRepository);
        verifyNoMoreInteractions(fitnessTestMapper);
    }
}
