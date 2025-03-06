package com.pnu.system.identityaccess.api;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.common.search.dto.ReportSearchRequest;
import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import com.pnu.system.identityaccess.api.dto.UserDto;
import com.pnu.system.identityaccess.api.dto.UserResponseDto;
import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import com.pnu.system.identityaccess.api.validator.UserCreateValidator;
import com.pnu.system.identityaccess.api.validator.UserUpdateValidator;
import com.pnu.system.identityaccess.service.UserSearchService;
import com.pnu.system.identityaccess.service.UserService;
import com.pnu.system.identityaccess.service.elasticsearch.UserElasticsearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final UserSearchService searchService;
    private final UserElasticsearchService elasticsearchService;
    private final UserCreateValidator userCreateValidator;
    private final UserUpdateValidator userUpdateValidator;

    @InitBinder("userCreateRequest")
    public void initUserCreateRequestBinder(WebDataBinder binder) {
        binder.addValidators(userCreateValidator);
    }

    @InitBinder("userUpdateRequest")
    public void initUserUpdateRequestBinder(WebDataBinder binder) {
        binder.addValidators(userUpdateValidator);
    }

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return service.create(userCreateRequest);
    }

    @PutMapping
    public UserResponseDto update(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return service.update(userUpdateRequest);
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/list")
    public List<Map<String, Object>> getUsers(@Valid @RequestBody ReportSearchRequest request) {
        return searchService.search(request);
    }

    //SAMPLE
    @GetMapping("/list/filter")
    public List<UserDto> getListByFilter(@RequestParam String filter, @RequestParam UserType type) {
        return elasticsearchService.getListByFilter(filter, type);
    }

    @GetMapping("/{id}/internal/group/list")
    public List<String> getUserGroupIds(@PathVariable String id) {
        return service.getUserGroupIds(id);
    }
}
