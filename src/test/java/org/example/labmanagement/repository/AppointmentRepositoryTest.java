package org.example.labmanagement.repository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.Appointment;
import org.example.labmanagement.dto.CourseAndAppointment;
import org.example.labmanagement.dto.LabCountByDayofweekDTO;
import org.example.labmanagement.dto.LabCountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AppointmentRepositoryTest {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void save() {
        Appointment appointment = Appointment.builder()
                .teacher("""
                        {"id":"01JFXVYSMCG63TYK72DP1GBT25","name":"杨过"}
                        """)
                .course("""
                        {"id":"432","name":"大数据"}
                        """)
                .semester("24-1")
                .nature("1")
                .labId("5f6a4e8d6c40437a8f22c8c9")
                .labName("嵌入式系统实验室")
                .week(2)
                .dayofweek(2)
                .section(2)
                .build();
        appointmentRepository.save(appointment);

    }

    @Test
    void countLabByDayofweek() {
        List<LabCountByDayofweekDTO> labCountDTOList =  appointmentRepository.countLabByDayofweek(3);
        log.debug("{}",labCountDTOList.toString());
    }

    @Test
    void findAppointmentByTeacherId() {
        for (Appointment a : appointmentRepository.findAppointmentByTeacherId("01JFXVYSMCG63TYK72DP1GBT25")) {
            log.debug(a.toString());
        }
    }

    @Test
    void showAllAppointment() {
        for (Appointment a : appointmentRepository.findAppointmentByLabIdAndSemester("5f6a4e8d6c40437a8f22c8c9","24-1")) {
            log.debug(a.toString());
        }
    }

    @Test
    void findCourseAndAppointmentByTeacherIdAndSemester() {
        for (CourseAndAppointment ca : appointmentRepository.findCourseAndAppointmentByTeacherIdAndSemester("01JFXVYSMCG63TYK72DP1GBT25","24-1")) {
            log.debug(ca.toString());
        }
    }
}