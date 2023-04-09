package edu.kocaeli.actofgod_api.service;

import edu.kocaeli.actofgod_api.converter.UserConverter;
import edu.kocaeli.actofgod_api.dto.UserDto;
import edu.kocaeli.actofgod_api.exception.ErrorStatusCode;
import edu.kocaeli.actofgod_api.exception.GeneralException;
import edu.kocaeli.actofgod_api.model.User;
import edu.kocaeli.actofgod_api.repository.UserRepository;
import edu.kocaeli.actofgod_api.util.EmailValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public UserDto add(UserDto userDto) {
        if (!EmailValidationUtil.isEmailValid(userDto.getEmail())) {
            throw new GeneralException(ErrorStatusCode.INVALID_EMAIL);
        }
        var forSave = userConverter.toEntity(userDto);
        forSave.setCreatedDate(new Date());
        return userConverter.toDto(userRepository.save(forSave));
    }

    public UserDto getById(Long id) {
        var userById = getUserIfFound(id);
        return userConverter.toDto(userById);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userConverter::toDto).collect(Collectors.toList());
    }

    public UserDto update(Long id, UserDto dto) {
        var forUpdateUser = getUserIfFound(id);
        forUpdateUser.setFirstName(dto.getFirstName());
        forUpdateUser.setLastName(dto.getLastName());
        forUpdateUser.setEmail(dto.getEmail());
        forUpdateUser.setPhoneNumber(dto.getPhoneNumber());
        forUpdateUser.setUpdatedDate(new Date());
        return userConverter.toDto(userRepository.save(forUpdateUser));
    }

    private User getUserIfFound(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new GeneralException(ErrorStatusCode.USER_NOT_FOUND_BY_ID)
        );
    }


}