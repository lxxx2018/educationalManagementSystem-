package com.xiaozhi.controller;

import com.xiaozhi.model.StudentView;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.result.resultImpl.WebResult;
import com.xiaozhi.service.LoginService;
import com.xiaozhi.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 小智 on 2017/4/18 0018.
 */
@Controller
@RequestMapping("login")
public class LoginController{
    @Resource
    private LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
    public WebResult studentLogin(@RequestParam String studentId, @RequestParam String password,
                                  HttpServletResponse response, HttpServletRequest request){
        ServiceResult<StudentVo> result = loginService.studentLogin(studentId, password);
        StudentVo studentVo = null;
        if (!result.isSuccess())
            return new WebResult(ResultCode.FIELD);
        studentVo = result.getData();
        StudentView studentView = new StudentView(studentVo);
        request.getSession().setAttribute("student", studentView);
        Cookie cookie = new Cookie("student", JsonUtils.object2json(studentVo));
        cookie.setMaxAge(60 * 30);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new WebResult();
    }
}
