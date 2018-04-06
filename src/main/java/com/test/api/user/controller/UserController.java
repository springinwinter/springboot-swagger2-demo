package com.test.api.user.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.enu.ResultCodeEnum;
import com.test.respvo.ApiReturnInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import com.test.entity.User;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/users")
@Api(value = "用户测试信息", tags = {"用户测试相关接口"})//swagger控制器说明注解
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    static {
        User user = new User();
        user.setAge(22);
        user.setName("222");
        user.setId(2l);
        users.put(user.getId(), user);
    }

    @ApiOperation(value = "获取用户列表", notes = "根据传入条件筛选用户")
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public ApiReturnInfo<List<User>> getUserList(@ApiIgnore ApiReturnInfo resp) {
        List<User> r = new ArrayList<User>(users.values());
        resp.setCode(ResultCodeEnum.RESULT_CODE_SUCCESS.getCode());
        resp.setMessage(ResultCodeEnum.RESULT_CODE_SUCCESS.getMessage());
        resp.setData(r);
        return resp;
    }

    /**
     * 入参是对象
     * 使用默认参数paramType = "body"
     * 参数@使用RequestParam，默认required=true
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postUser(User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息，非restful风格",notes = "入参是基础数据类型，使用默认参数paramType = query")
    @ApiImplicitParam(name = "id2", value = "用户ID", required = true, dataType = "Long",paramType = "query")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public User getUser(@ApiIgnore HttpSession session,Long id2, User user) {
        return users.get(id2);
    }

    /**
     * * 入参是对象,使用默认参数paramType = "body"
     * ApiIgnore()用于类，方法，方法参数
     * 表示这个方法或者类被忽略,不被显示在swagger页面上
     * @param resp
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@ApiIgnore ApiReturnInfo resp,@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
