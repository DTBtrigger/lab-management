package org.example.labmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.Appointment;
import org.example.labmanagement.dox.User;
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
    public ResultVO showAllAppointments(@PathVariable("labId") String labId, String semester) {
        List<Appointment> appointments = userService.showAllAppointments(labId,semester);
        return ResultVO.succuss(appointments);
    }

    @PostMapping("{account}/{labId}/appointments")
    public ResultVO makeAAppointment(@RequestAttribute("uid") String teacherId,@PathVariable String account,@RequestBody Appointment appointment) {
        User userR = userService.findUserByAccount(account);

        if (!userR.getId().equals(teacherId)) {
            return ResultVO.error(Code.FORBIDDEN);
        }
        userService.makeAAppointment(appointment);
        return ResultVO.succuss(appointment);
    }


}
