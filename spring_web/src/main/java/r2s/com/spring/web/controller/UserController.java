package r2s.com.spring.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.CreateUserRequestDTO;
import r2s.com.spring.web.dto.request.UpdateUserRequestDTO;
import r2s.com.spring.web.dto.response.PagingUserListResponseDTO;
import r2s.com.spring.web.dto.response.UserResponseDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/get-all")
    public ResponseEntity getAllUser(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size,
                                     @RequestParam(value = "sort", required = false) String type_sort) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1);
        userResponseDTO.setUsername("hieu01");
        userResponseDTO.setName("Nguyen Huu Hieu");
        userResponseDTO.setEmail("hieu01@gmail.com");
        userResponseDTO.setPhone("0123456789");
        userResponseDTO.setGender("male");
        userResponseDTO.setBirthday(Date.valueOf("2001-02-02"));
        userResponseDTO.setRole("USER");

        UserResponseDTO userResponseDTO1 = new UserResponseDTO();
        userResponseDTO1.setId(2);
        userResponseDTO1.setUsername("hieu02");
        userResponseDTO1.setName("Huu Hieu");
        userResponseDTO1.setEmail("hieu02@gmail.com");
        userResponseDTO1.setPhone("0123456789");
        userResponseDTO1.setGender("male");
        userResponseDTO1.setBirthday(Date.valueOf("2002-05-02"));
        userResponseDTO1.setRole("USER");

        PagingUserListResponseDTO response = new PagingUserListResponseDTO();
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        userResponseDTOList.add(userResponseDTO);
        userResponseDTOList.add(userResponseDTO1);

        response.setUserResponseDTOList(userResponseDTOList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(type_sort);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertUser(@RequestBody CreateUserRequestDTO createUserRequestDto) {
        String username = createUserRequestDto.getUsername();
        String name = createUserRequestDto.getName();
        String phone = createUserRequestDto.getPhone();
        String email = createUserRequestDto.getEmail();
        String gender = createUserRequestDto.getGender();
        String role = createUserRequestDto.getRole();
        Date birthday = createUserRequestDto.getBirthday();

        UserResponseDTO response = new UserResponseDTO();
        response.setId(1);
        response.setUsername(username);
        response.setName(name);
        response.setPhone(phone);
        response.setEmail(email);
        response.setGender(gender);
        response.setBirthday(birthday);
        response.setRole(role);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getDetailUserByUserId(@PathVariable(value = "user-id") int userId) {
        UserResponseDTO response = new UserResponseDTO();
        response.setId(userId);
        response.setUsername("hieu01");
        response.setName("Nguyen Huu Hieu");
        response.setEmail("hieu01@gmail.com");
        response.setPhone("0123456789");
        response.setGender("male");
        response.setBirthday(Date.valueOf("2001-02-02"));
        response.setRole("USER");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{user-id}")
    public ResponseEntity updateUser(@PathVariable(value = "user-id") int userId,
                                     @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        UserResponseDTO response = new UserResponseDTO();
        response.setId(userId);
        response.setUsername(updateUserRequestDTO.getUsername());
        response.setName(updateUserRequestDTO.getName());
        response.setEmail(updateUserRequestDTO.getEmail());
        response.setPhone(updateUserRequestDTO.getPhone());
        response.setGender(updateUserRequestDTO.getGender());
        response.setBirthday(updateUserRequestDTO.getBirthday());
        response.setRole("USER");

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable(value = "user-id") int userId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
