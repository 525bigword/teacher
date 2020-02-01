package com.teacher.study.service.impl;

import com.teacher.study.dao.ClassIfyMapper;
import com.teacher.study.enetity.ClassIfy;
import com.teacher.study.enetity.ClassIfyModel;
import com.teacher.study.enetity.Power;
import com.teacher.study.service.ClassIfyService;
import com.teacher.study.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ClassIfyServiceImpl implements ClassIfyService {
    @Autowired
    private ClassIfyMapper classIfyMapper;

    @Override
    public List<ClassIfyModel> findCourseWareAll() throws Exception {
        List<ClassIfyModel> classIfyAll = classIfyMapper.findClassIfyAll(0);

        return classIfyAll;
    }

    @Override
    public List<Map<String,Object>> findCourseWareAllNoZero(Integer userid) throws Exception {
        List<Power> classIfyAllToZero = classIfyMapper.findClassIfyAllToZero(userid);
        List<Map<String,Object>> classIfies=new ArrayList<>();
        for (int i = 0; i < classIfyAllToZero.size(); i++) {
            ClassIfy classIfyModel = classIfyAllToZero.get(i).getClassIfy();
            Map<String,Object> stringObjectMap=new ConcurrentHashMap<>();
            stringObjectMap.put("id",classIfyModel.getId());
            stringObjectMap.put("name",classIfyModel.getName());
            System.out.println(stringObjectMap.get("id"));
            classIfies.add(i,stringObjectMap);
        }
        return classIfies;
    }

    @Override
    public List<ClassIfyModel> findCourseWareByUser(Integer userid) throws Exception {
        List<ClassIfyModel> classIfyAll = classIfyMapper.findClassIfyAll(userid);

        return classIfyAll;
    }

    @Override
    public List<ClassIfyModel> findCourseWareTochild(Integer ClassIf) {
        ClassIfyModel classIfy= new ClassIfyModel();
        classIfy.setSuperiorid(ClassIf);
        return classIfyMapper.findClassIfyAll(ClassIf);
    }

    /**
     * 新增分类业务
     * @param classIfy
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveClassiIfy(ClassIfy classIfy) throws Exception {
        try{
            Integer order = classIfyMapper.findOrder(classIfy);
            //排序自增
            if(null==order){
                classIfy.setOrder(1);
                classIfyMapper.saveClassIfy(classIfy);

            }else{
                classIfy.setOrder(++order);
                classIfyMapper.saveClassIfy(classIfy);
                System.out.println(classIfy);
            }
        }catch (Exception e){
            classIfy.setOrder(0);
            classIfyMapper.saveClassIfy(classIfy);
            e.printStackTrace();
            log.info("com.teacher.study.service.impl.ClassIfyServiceImpl.saveClassiIfy方法sql异常");
            throw new Exception();
        }
    }
    /**
     * 修改分类业务
     * @param classIfy
     * @throws Exception
     */
    @Transactional
    @Override
    public void upClassiIfy(ClassIfy classIfy) throws Exception {
        classIfy.setUpdatetime(DateUtil.getTime());
        classIfyMapper.update(classIfy);
    }
    private void delClassIfy(ClassIfy classIfy){
        List<ClassIfy> classIfyBySuperioId = classIfyMapper.findClassIfyBySuperioId(classIfy);
        for (ClassIfy ify : classIfyBySuperioId) {
            delClassIfy(ify);
        }
        if(classIfyBySuperioId.size()==1){
            return;
        }
        classIfyMapper.delClassIfyById(classIfy);

    }
    /**
     * 删除分类业务
     * @param classIfy
     * @throws Exception
     */
    @Transactional
    @Override
    public void delClassiIfy(ClassIfy classIfy) throws Exception {
        List<ClassIfy> classIfyBySuperioId = classIfyMapper.findClassIfyBySuperioId(classIfy);

        for (ClassIfy ify : classIfyBySuperioId) {
            delClassIfy(ify);
        }
        classIfyMapper.delClassIfyById(classIfy);
    }
    /**
     * 查询所有分类
     * @throws Exception
     */
    @Override
    public List<ClassIfyModel> getClassiIfyAll() throws Exception {
        List<ClassIfyModel> classIfyToZero = classIfyMapper.findClassIfyToParent();
        for (ClassIfyModel ify : classIfyToZero) {
            List<ClassIfyModel> classIfyAll = classIfyMapper.findClassIfyAll(ify.getId());
            ify.setClassIfyModels(classIfyAll);
        }
        return classIfyToZero;
    }

    @Override
    public List<ClassIfy> getClassiIfyToZero(Integer id) throws Exception {
        ClassIfy classIfyToZero = classIfyMapper.findClassIfyToZero(id);
        List<ClassIfy> classIfyBySuperioId = classIfyMapper.findClassIfyBySuperioId(classIfyToZero);
        return classIfyBySuperioId;
    }
}
