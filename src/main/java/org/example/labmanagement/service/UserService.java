package org.example.labmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.Appointment;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.exception.XException;
import org.example.labmanagement.repository.AppointmentRepository;
import org.example.labmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
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
}
//查看所有用户，添加用户，重置密码