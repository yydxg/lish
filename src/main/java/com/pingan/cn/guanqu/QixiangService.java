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

@Service("qixiangService")
public class QixiangService {
    @Autowired
    private QixiangDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public Qixiang saveAction(Qixiang person){
        Qixiang save = null;
        try {
            return actionDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Qixiang action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Qixiang> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Qixiang oldAction = actionDao.findById(action.getId()).get();
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

    public List<Qixiang> findAll(){
        return actionDao.findAll();
    }

    public Qixiang findById(String id){
        return actionDao.findById(id).get();
    }

    public Qixiang findCurrent(){
        List<Qixiang> actions = findAll();
        //降序
        List<Qixiang> collect = actions.stream().sorted(new Comparator<Qixiang>() {
            @Override
            public int compare(Qixiang o1, Qixiang o2) {
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

        System.out.println(Arrays.toString(actions.toArray()));
        return collect.get(0);
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
        ArrayList<Qixiang> arrayList = new ArrayList<>();
        for (String id:ids) {
            Qixiang entity = findById(id);
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
}
