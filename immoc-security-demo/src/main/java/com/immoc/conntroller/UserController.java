package com.immoc.conntroller;

import com.immoc.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @ApiOperation("用户查询服务")
    public String getUser(@ApiParam("用户ID") String id){
        return "zhang";
    }

    @PostMapping
    @ApiOperation("用户注册")
    public User add( User user){
        return user;
    }
}
