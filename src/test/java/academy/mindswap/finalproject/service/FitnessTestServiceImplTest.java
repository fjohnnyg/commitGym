package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.FitnessTestCreateDtoBySchedule;
import academy.mindswap.finalproject.mapper.FitnessTestMapper;
import academy.mindswap.finalproject.model.entities.FitnessTest;
import academy.mindswap.finalproject.repository.FitnessTestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FitnessTestServiceImplTest {

    @Mock
    private FitnessTestMapper fitnessTestMapper;

    @Mock
    private FitnessTestRepository fitnessTestRepository;

    @InjectMocks
    private FitnessTestServiceImpl fitnessTestService;

    private FitnessTestCreateDtoBySchedule fitnessTestCreateDtoBySchedule;
    private FitnessTest fitnessTest;

    @BeforeEach
    public void setup() {
        // Create test objects
        fitnessTestCreateDtoBySchedule = FitnessTestCreateDtoBySchedule.builder()
                .date(LocalDate.now())
                .id(123L)
                .build();
        fitnessTest = new FitnessTest();
        fitnessTest.setId(123L);

        // Mock the mapper's behavior
        when(fitnessTestMapper.fromFitnessTestCreateDtoScheduleToEntity(fitnessTestCreateDtoBySchedule))
                .thenReturn(fitnessTest);
    }

    @Test
    public void testSchedule() {
        // Call the method being tested
        fitnessTestService.schedule(123L, fitnessTestCreateDtoBySchedule);

        // Verify that the repository's save method was called with the correct object
        verify(fitnessTestRepository).save(fitnessTest);
    }
}
