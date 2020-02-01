package com.teacher.study.controller;

import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.Power;
import com.teacher.study.enetity.User;
import com.teacher.study.service.ClassIfyService;
import com.teacher.study.service.PowerService;
import com.teacher.study.util.DateUtil;
import com.teacher.study.util.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("power")
public class PowerController {
    @Autowired
    private PowerService powerService;



    @PostMapping("/add")
    public Return save(@RequestBody  Power power){
        if(null==power.getUserid()||"".equals(power.getClassifyid())){
            return new Return().custom("分类或用户不能为空");
        }else{
            try {
                power.setCreatetime(DateUtil.getTime());
                power.setUpdatetime(DateUtil.getTime());
                powerService.savePower(power);
                return new Return().yes("");
            } catch (Exception e) {
                return new Return().no();
            }
        }
    }
}
