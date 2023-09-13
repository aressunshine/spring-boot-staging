package com.bruce.staging.controller;

import com.bruce.staging.domain.UserInfo;
import com.bruce.staging.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_info")
@Tag(name = "用户信息-控制器")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/{id}")
    @Operation(
            summary = "根据ID查询用户",
            parameters = {@Parameter(name = "id", required = true, in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserInfo.class))),
                    @ApiResponse(responseCode = "400", description = "错误", content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<UserInfo> getUserById(@PathVariable Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return ResponseEntity.ok(userInfo);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "根据ID删除用户",
            parameters = {
                    @Parameter(name = "id", required = true, in = ParameterIn.PATH)
            }
    )
    public ResponseEntity<UserInfo> deleteUserById(@PathVariable Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/")
    @Operation(
            summary = "新增用户",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "请求描述", required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfo.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserInfo.class))),
                    @ApiResponse(responseCode = "400", description = "错误", content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<UserInfo> body(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfo);
    }

    @Operation(summary = "根据用户ID更新用户")
    @Parameters({
            @Parameter(name = "id", description = "文件id", required = true, in = ParameterIn.PATH),
            @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "name", description = "文件名称", required = true, in = ParameterIn.QUERY)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserInfo> bodyParamHeaderPath(@PathVariable("id") String id,
                                                        @RequestHeader("token") String token,
                                                        @RequestParam("name") String name,
                                                        @RequestBody UserInfo userInfo) {
        userInfo.setUsername(id + "-" + name + "-" + token);
        return ResponseEntity.ok(userInfo);
    }
}
