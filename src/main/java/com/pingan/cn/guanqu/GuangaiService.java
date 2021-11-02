package com.pingan.cn.guanqu;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("guangaiService")
public class GuangaiService {
    @Autowired
    private GuangaiDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public Guangai saveAction(Guangai person){
        Guangai save = null;
        try {
            return actionDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Guangai action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Guangai> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Guangai oldAction = actionDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                actionDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Guangai> findAll(){
        return actionDao.findAll();
    }

    public Guangai findById(String id){
        return actionDao.findById(id).get();
    }

    public boolean deleteById(String id){
        try {
            actionDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBatch(String[] ids){
        ArrayList<Guangai> arrayList = new ArrayList<>();
        for (String id:ids) {
            Guangai entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            actionDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Map<String,Guangai> findCurrent(){
        List<Guangai> actions = findAll();

        Map<String, List<Guangai>> collects = actions.stream().collect(Collectors.groupingBy(Guangai::getType));
        Map<String,Guangai> resutMap = new HashMap<>();

        for (String key : collects.keySet()) {
            System.out.println("key= "+ key + " and value= " + collects.get(key));
            //降序
            List<Guangai> collect = collects.get(key).stream().sorted(new Comparator<Guangai>() {
                @Override
                public int compare(Guangai o1, Guangai o2) {
                    try {
                        Date d1 = sdf.parse(o1.getDate());
                        Date d2 = sdf.parse(o2.getDate());
                        return d2.compareTo(d1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            }).collect(Collectors.toList());
            resutMap.put(key,collect.get(0));
        }
        return resutMap;
    }
}
