package academy.mindswap.finalproject.converter;

import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.model.Role;
import academy.mindswap.finalproject.model.Specializations;
import academy.mindswap.finalproject.model.User;


public class UserConverter {

    public UserDto fromUserEntityToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .specializations(user.getSpecializations())
                .build();
    }


    public User fromUserCreateDtoToEntity(UserCreateDto userDto){
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .birthDate(userDto.getBirthDate())
                .email(userDto.getEmail())
                .userName(userDto.getUserName())
                .role(Role.CLIENT)
                .password(userDto.getPassword())
                .specializations(Specializations.YOGA)
                .build();
    }

}
