package com.teacher.study.service;

import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.Power;
import com.teacher.study.enetity.User;

import java.util.List;
import java.util.Map;

public interface PowerService {
    //新增Power
    void savePower(Power power)throws Exception;
    //查询power
    Map<String,List> findPowerByUserIdAndnamepage(Integer classify_id, String name, Integer index, Integer pageNum)throws Exception;
    //根据id查询power数量
    Integer findPowerByIdCount(List<Integer> id,String name);
}
