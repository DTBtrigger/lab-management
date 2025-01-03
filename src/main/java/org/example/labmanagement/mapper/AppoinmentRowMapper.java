package org.example.labmanagement.mapper;

import org.example.labmanagement.dox.Appointment;
import org.example.labmanagement.dox.Course;
import org.example.labmanagement.dto.CourseAndAppointment;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppoinmentRowMapper implements RowMapper<CourseAndAppointment> {
    @Override
    public CourseAndAppointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = Course.builder()
                .id(rs.getString("c.id"))
                .name(rs.getString("c.name"))
                .quantity(rs.getInt("c.quantity"))
                .semester(rs.getString("c.semester"))
                .clazz(rs.getString("c.clazz"))
                .type(rs.getInt("c.type"))
                .teacherId(rs.getString("c.teacher_id"))
                .experimentHour(rs.getInt("c.experiment_hour"))
                .build();
        Appointment appointment = Appointment.builder()
                .id(rs.getString("a.id"))
                .teacher(rs.getString("a.teacher"))
                .course(rs.getString("a.course"))
                .semester(rs.getString("a.semester"))
                .nature(rs.getString("a.nature"))
                .labId(rs.getString("a.lab_id"))
                .labName(rs.getString("a.lab_name"))
                .week(rs.getInt("a.week"))
                .dayofweek(rs.getInt("a.dayofweek"))
                .section(rs.getInt("a.section"))
                .build();
        return CourseAndAppointment.builder()
                .course(course)
                .appointment(appointment)
                .build();
    }
}
