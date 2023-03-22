package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto fromUserEntityToUserDto(User user);
    User fromUserCreateDtoToEntity(UserCreatedDto userCreatedDto);
}
