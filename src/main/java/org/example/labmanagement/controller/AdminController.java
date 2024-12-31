package org.example.labmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dto.LabCountDTO;
import org.example.labmanagement.service.AdminService;
import org.example.labmanagement.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name = "测试管理员接口")
@Slf4j
@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "显示实验室状态")
    @GetMapping("labstate")
    public ResultVO accountLabByState() {
        Map<String,List<?>> labState = adminService.getLabState(2);
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

}
