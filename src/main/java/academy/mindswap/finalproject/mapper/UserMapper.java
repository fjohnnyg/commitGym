package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.PTCreateDto;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.model.Role;
import academy.mindswap.finalproject.model.Specializations;
import academy.mindswap.finalproject.model.User;
import jakarta.validation.groups.Default;
import lombok.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    default UserDto fromUserEntityToUserDto(User user){
        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.email( user.getEmail() );
        userDto.role(Role.CLIENT);

        return userDto.build();
    };
    User fromUserCreateDtoToEntity(UserCreateDto userCreatedDto);
    default PTCreateDto fromPTCreateDtoToEntity(PTCreateDto ptCreateDto){
        PTCreateDto.PTCreateDtoBuilder pTCreateDto = PTCreateDto.builder();

        pTCreateDto.firstName( ptCreateDto.getFirstName() );
        pTCreateDto.lastName( ptCreateDto.getLastName() );
        pTCreateDto.birthDate( ptCreateDto.getBirthDate() );
        pTCreateDto.email( ptCreateDto.getEmail() );
        pTCreateDto.userName( ptCreateDto.getUserName() );
        pTCreateDto.role(Role.PERSONAL_TRAINER);
        pTCreateDto.password( ptCreateDto.getPassword() );
        pTCreateDto.specializations(Specializations.GENERIC);

        return pTCreateDto.build();
    };

}
