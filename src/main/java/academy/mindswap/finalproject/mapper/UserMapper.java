package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.ClientCreateDto;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDeleteAccountDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.model.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
   UserDto fromUserEntityToUserDto(User user);
   User fromUserCreateDtoToEntity(UserCreateDto userCreatedDto);
   User fromClientCreateDtoToEntity(ClientCreateDto clientCreateDto);
   ClientCreateDto fromUserEntityToclientCreateDto(User user);
   User fromUserDtoToEntity(UserDto userDto);
   User fromUserDeleteAccountDtoToEntity(UserDeleteAccountDto userDeleteAccountDto);
   User fromEntityToUserDeleteAccountDto(User user);

}
