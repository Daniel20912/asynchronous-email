package com.danieloliveira.user_service.service;

import com.danieloliveira.user_service.dto.UserDto;
import com.danieloliveira.user_service.kafka.KafkaProducer;
import com.danieloliveira.user_service.model.User;
import com.danieloliveira.user_service.repository.UserRepository;
import com.danieloliveira.user_service.utils.JsonUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaProducer producer;
    private final JsonUtil jsonUtil;

    public User createUser(UserDto userDto) {
        User user = toUser(userDto);
        userRepository.save(user);
        producer.sendMessage(jsonUtil.toJson(userDto));
        return user;
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        updateUserEntity(user, userDto);
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.deleteById(id);
    }


    private User toUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }

    private void updateUserEntity(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
    }
}
