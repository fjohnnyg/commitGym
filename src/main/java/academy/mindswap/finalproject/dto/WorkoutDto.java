package academy.mindswap.finalproject.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDto {

    private int set;
    private int rep;
    private int rest;
    private int load;
    private String exerciseName;



}
