package work.pcdd.securityjwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.securityjwt.common.util.JwtUtils;
import work.pcdd.securityjwt.model.entity.UserInfo;
import work.pcdd.securityjwt.model.vo.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pcdd
 * @date 2022/6/5 周日
 */
@RestController
public class TestController {

    @Autowired
    JwtUtils jwtUtils;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/fun1")
    public Result fun1() {
        return Result.success("当前正在访问系统的用户的详细信息", SecurityContextHolder.getContext().getAuthentication());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/fun2")
    public Result fun2() {
        return Result.success("只要认证（登录），此接口就会调用成功");
    }

    //@Secured("ROLE_admin")
    // 这样写的话，admin就无法调用此接口了，若想接口user和admin都调用，不写@PreAuthorize注解即可
    @PreAuthorize("hasRole('user')")
    @GetMapping("/fun3")
    public Result fun3() {
        return Result.success("user接口调用成功！");
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/fun4")
    public Result fun4() {
        return Result.success("admin接口调用成功！");
    }

    @GetMapping("/fun5")
    public Result fun5() {
        return Result.success("没有加任何权限注解，此接口谁都可以调用");
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/fun6")
    public Result fun6() {
        return Result.success("这个接口只有匿名用户才能调用，已认证的用户反而无法调用");
    }

    @GetMapping("/token")
    public Result token() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setRole("admin");
        Map<String, Object> map = new HashMap<>();
        map.put("user", userInfo);
        map.put("token", jwtUtils.generateToken(userInfo));
        return Result.success(map);
    }

}
