package com.whiteslave.whiteslaveApp.controller;

import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.user.UserFacade;
import com.whiteslave.whiteslaveApp.user.domain.dto.CreateUserDto;
import com.whiteslave.whiteslaveApp.user.domain.dto.UserDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/test")
    @ApiOperation(value = "FILL IT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    public ResponseEntity<Object> findAllSyncCheckReports() {
        Map<String, Object> response = new HashMap<>();
        response.put("id", UUID.randomUUID().toString());
        response.put("content", "Hello World");
        log.info("Response test-> " + response);
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create new authorization user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Auth user created and saved to data base successful"),
            @ApiResponse(code = 409, message = "Auth user already exists, can't create"),
            @ApiResponse(code = 503, message = "Data base server error. Can't create auth user.")})
    ResponseEntity<Object> create(@RequestBody CreateUserDto createUserDto) {
        UserDto createdAuthUser = userFacade.createUser(createUserDto);
        URI savedUri = ServletUriComponentsBuilder
                //todo naprawic
                .fromCurrentRequestUri()
                .query("id={id}")
                .buildAndExpand(createdAuthUser.getId())
                .toUri();
        return ResponseEntity.created(savedUri)
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
    ResponseEntity<Object> findAll() {
        List<UserDto> userDtoList = userFacade.findAll();
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(userDtoList);
    }

}
