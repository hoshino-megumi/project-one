package com.example.springbootdemo.controller;

import com.example.springbootdemo.api.CommonResult;
import com.example.springbootdemo.dao.LoginDao;
import com.example.springbootdemo.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user) {
        User u = LoginDao.LoginCheck(user.getUsername(), user.getPassword());
        if (user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword()))
//        if (user == LoginDao.LoginCheck(user.getUsername().trim(), user.getPassword().trim()))
//        if (user.getUsername().equals("test") && user.getPassword().equals("123456"))
            return CommonResult.success("登录成功");
        else
            return CommonResult.validateFailed();
    }
}

