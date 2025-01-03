package org.example.labmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.labmanagement.dox.Appointment;
import org.example.labmanagement.dox.Course;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseAndAppointment {
    Course course;
    Appointment appointment;
}
