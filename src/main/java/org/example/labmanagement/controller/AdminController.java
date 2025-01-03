package org.example.labmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.dto.LabCountDTO;
import org.example.labmanagement.service.AdminService;
import org.example.labmanagement.service.UserService;
import org.example.labmanagement.vo.ResultVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "测试管理员接口")
@Slf4j
@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Operation(summary = "显示实验室状态")
    @GetMapping("labstate")
    public ResultVO showLabState(@RequestBody int week) {
        Map<String,List<?>> labState = adminService.getLabState(week);
        return ResultVO.succuss(labState);
    }

    @Operation(summary = "查看所有公告")
    @GetMapping("news")
    public ResultVO findAllNews() {
        return ResultVO.succuss(adminService.findAllNews());
    }
    @Operation(summary = "查看所有实验室信息")
    @GetMapping("labs")
    public ResultVO findAllLabs() {
        return ResultVO.succuss(adminService.findAllLabs());
    }

    @GetMapping("users")
    public ResultVO findAllUsers() {
        return ResultVO.succuss(adminService.findAllUsers());
    }

    //添加用户
    @PostMapping("users")
    public ResultVO addSingleUser(@RequestBody User user) {
        adminService.addSingleUser(user);
        return ResultVO.succuss(user);
    }

    //将用户密码重置为其账号
    //users页面中为users列表，点开某个项通过弹窗查看具体信息，因此更改具体信息直接在users页面更改
    @PatchMapping("users")
    public ResultVO resetPassword(@RequestBody User user) {
        if (user.getId() != null && user.getAccount() != null){
            userService.changePassword(user.getId(),user.getAccount());
            return ResultVO.succuss("修改成功");
        }
        return ResultVO.error(500,"传值为空");
    }

    @DeleteMapping("news")
    public ResultVO deletenews(@RequestBody List<String> newsId) {
        adminService.deleteNews(newsId);
        return ResultVO.ok();
    }

}
