package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.model.classes.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
   UserDto fromUserEntityToUserDto(User user);
   User fromUserCreateDtoToEntity(UserCreateDto userCreatedDto);

    /*{
        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.email( user.getEmail() );
        userDto.role(Role.CLIENT);

        return userDto.build();
    };

     */


    /*PTCreateDto fromPTCreateDtoToEntity(PTCreateDto ptCreateDto)
    {

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

     */




}
