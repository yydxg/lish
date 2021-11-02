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

@Service("shuiweiService")
public class ShuiweiService {
    @Autowired
    private ShuiweiDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public Shuiwei saveAction(Shuiwei person){
        Shuiwei save = null;
        try {
            return actionDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Shuiwei action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Shuiwei> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Shuiwei oldAction = actionDao.findById(action.getId()).get();
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

    public List<Shuiwei> findAll(){
        return actionDao.findAll();
    }

    public Shuiwei findById(String id){
        return actionDao.findById(id).get();
    }

    public Shuiwei findBySTNum(String STnum){
        List<Shuiwei> lists = actionDao.findBySTNum(STnum);
        //降序
        List<Shuiwei> collect = lists.stream().sorted(new Comparator<Shuiwei>() {
            @Override
            public int compare(Shuiwei o1, Shuiwei o2) {
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
        if(collect.size()>0){
            return collect.get(0);
        }else {
            return null;
        }
    }

    public Shuiwei findCurrent(){
        List<Shuiwei> actions = findAll();
        //降序
        List<Shuiwei> collect = actions.stream().sorted(new Comparator<Shuiwei>() {
            @Override
            public int compare(Shuiwei o1, Shuiwei o2) {
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
        ArrayList<Shuiwei> arrayList = new ArrayList<>();
        for (String id:ids) {
            Shuiwei entity = findById(id);
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
