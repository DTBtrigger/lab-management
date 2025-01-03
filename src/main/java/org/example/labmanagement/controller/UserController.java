package org.example.labmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.Appointment;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.dto.CourseAndAppointment;
import org.example.labmanagement.exception.Code;
import org.example.labmanagement.service.UserService;
import org.example.labmanagement.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
    private final UserService userService;

    //在首页即能查看自己的所有预约
    @GetMapping("{account}")
    public ResultVO findAppointment(@RequestAttribute("uid") String teacherId ,@PathVariable String account) {
        User userR = userService.findUserByAccount(account);

        if (!userR.getId().equals(teacherId)) {
            return ResultVO.error(Code.FORBIDDEN);
        }
        List<Appointment> appointments = userService.findAppointmentsByTeacherId(teacherId);
        return ResultVO.succuss(appointments);
    }

    @GetMapping("{account}/{labId}/appointments")
    public ResultVO showAllAppointments(@PathVariable("labId") String labId, @RequestBody String semester) {
        List<Appointment> appointments = userService.showAllAppointments(labId,semester);
        return ResultVO.succuss(appointments);
    }

    @PostMapping("{account}/appointments")
    public ResultVO makeAAppointment(@RequestAttribute("uid") String teacherId,@PathVariable String account,@RequestBody Appointment appointment) {
        User userR = userService.findUserByAccount(account);

        if (!userR.getId().equals(teacherId)) {
            return ResultVO.error(Code.FORBIDDEN);
        }
        userService.makeAAppointment(appointment);
        return ResultVO.succuss(appointment);
    }

    //修改个人密码
    @PatchMapping("{account}/settings/password")
    public ResultVO changePassword(@RequestAttribute("uid") String userId, @RequestBody String newPassword,@PathVariable("account") String account) {
        User userR = userService.findUserByAccount(account);
        if (userR.getId() != userId){
            ResultVO.error(Code.FORBIDDEN);
        }
        userService.changePassword(userId,newPassword);
        return ResultVO.succuss(newPassword);
    }

    //查看当前预约
    @GetMapping("teacher/currentappointment/{semester}")
    public ResultVO showCurrentAppointment(@RequestAttribute("uid") String teacherId,@PathVariable("semester") String semester ) {
        List<CourseAndAppointment> courseAndAppointments = userService.showCurrentAppointment(teacherId,semester);
        if (courseAndAppointments.isEmpty()) {
            return ResultVO.succuss("您当前没有预约");
        }
        return ResultVO.succuss(courseAndAppointments);
    }
}
