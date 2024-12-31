package org.example.labmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.dto.*;
import org.example.labmanagement.interceptor.AdminInterceptor;
import org.example.labmanagement.repository.*;
import org.example.labmanagement.repository.LabRepository;
import org.example.labmanagement.vo.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final LabRepository labRepository;
    private final AppointmentRepository  appointmentRepository;
    private final NewsRepository newsRepository;

    @Transactional
    public void addSingleUser(User user) {
        userRepository.save(user);
    }


    @Transactional
    public Map<String, List<?>> getLabState(int week) {
//        查看每个状态的实验室数
        List<LabCountDTO>  labCountDTOList = labRepository.countLabByState();
//        查看每周每一天有多少个实验室使用（不看节次，只要有课就是使用）
        List<LabCountByDayofweekDTO> labCountByDayofweekDTOList = appointmentRepository.countLabByDayofweek(week);
//        查看每个实验室能用的设备数量
        List<EnableEquipmentCount> enableEquipmentCountList = labRepository.countEnableEquipment();
        return Map.of("labCountDTOList",labCountDTOList,
                "labCountByDayofweekDTOList",labCountByDayofweekDTOList,
                "enableEquipmentCountList",enableEquipmentCountList);
    }

    @Transactional
    public List<NewsDTO> findAllNews() {
        return newsRepository.findAllNews();
    }

    @Transactional
    public List<LabDTO> findAllLabs() {
        return labRepository.findAllLabs();
    }

}
