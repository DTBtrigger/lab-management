package org.example.labmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.Appointment;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.dto.CourseAndAppointment;
import org.example.labmanagement.exception.XException;
import org.example.labmanagement.repository.AppointmentRepository;
import org.example.labmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }
    //基于账号查找用户
    @Transactional
    public User findUserByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }
    //基于教室id查找教室本学期所有
    @Transactional
    public List<Appointment> findAppointmentsByTeacherId(String teacherId) {
        return appointmentRepository.findAppointmentByTeacherId(teacherId);
    }

    //进行预约
    @Transactional
    public void makeAAppointment(Appointment appointment) {
        try {
            appointmentRepository.save(appointment);
        } catch (DbActionExecutionException e) {
            throw XException.builder().number(400).message("该时段已被选过").build();
        }

    }

    //基于实验室id和学期查询，本学期指定实验室的所有预约
    @Transactional
    public List<Appointment> showAllAppointments(String labId, String semester) {
        return appointmentRepository.findAppointmentByLabIdAndSemester(labId,semester);
    }

    //修改个人密码,传入的user参数的密码为，修改之后且未加密的
    @Transactional
    public void changePassword(String userId, String newPassword) {
        String encodePassword = passwordEncoder.encode(newPassword);
        log.debug("====================================");
        log.debug(newPassword);
        log.debug(encodePassword);
        log.debug("{}",passwordEncoder.matches(newPassword,encodePassword));
        log.debug("=====================================");
        userRepository.updatePasswordByUserId(userId,encodePassword);
    }

    //根据教师id与学期查看教师的预约以及预约课程的详细信息
    public List<CourseAndAppointment> showCurrentAppointment(String teacherId, String semester) {
        return appointmentRepository.findCourseAndAppointmentByTeacherIdAndSemester(teacherId,semester);
    }
}
//查看所有用户，添加用户，重置密码