package com.whiteslave.whiteslaveApp.controller;

import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.user.UserFacade;
import com.whiteslave.whiteslaveApp.user.domain.UserStatus;
import com.whiteslave.whiteslaveApp.user.domain.dto.CreateUserDto;
import com.whiteslave.whiteslaveApp.user.domain.dto.UserDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.whiteslave.whiteslaveApp.controller.headerHandler.HttpHeaderFactory.getSuccessfulHeaders;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(description = "User controller to ....")
@CrossOrigin(origins = "${cross.origin.webui}")
class UserController {

    private final UserFacade userFacade;

    @PostMapping("/create")
    @ApiOperation(value = "Create new authorization user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Auth user created and saved to data base successful"),
            @ApiResponse(code = 409, message = "Auth user already exists, can't create"),
            @ApiResponse(code = 503, message = "Data base server error. Can't create auth user.")})
    ResponseEntity<Object> create(@RequestBody CreateUserDto createUserDto) {
        UserDto createdAuthUser = userFacade.createUser(createUserDto);
        URI savedUri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/user/findbyid")
                .query("id={id}")
                .buildAndExpand(createdAuthUser.getId())
                .toUri();
        return ResponseEntity.created(savedUri)
                .headers(getSuccessfulHeaders())
                .body(createdAuthUser);
    }

    @GetMapping("/findall")
    @ApiOperation(value = "Search all users from data base", response = SearchReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    ResponseEntity<Object> findAll(HttpServletRequest request) {
        String authType = request.getAuthType();
        String header = request.getHeader("user-agent");
        List<UserDto> userDtoList = userFacade.findAll();
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(userDtoList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(readOnly = true, name = "id", value = "User ID from data base.",
                    dataTypeClass = String.class, paramType = "query"),
    })
    ResponseEntity<Object> delete(@RequestParam("id") String id) {

        userFacade.delete(Long.valueOf(id));
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .build();
    }

    @GetMapping("/validateuser")
    @ApiOperation(value = "validate user to login", response = UserStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User validated successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 401, message = "Unauthorized user not match"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    ResponseEntity<Object> validateUser() {
        UserStatus userStatus = new UserStatus("User successfully authenticated");
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(userStatus);
    }

}
