package com.pingan.cn.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.dao.ImagesearchDao;
import com.pingan.cn.entity.Imagesearch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("imagesearchService")
public class ImagesearchService {
    @Autowired
    private ImagesearchDao imageDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

    public Imagesearch save(Imagesearch person){
        Imagesearch save = null;
        try {
            return imageDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Imagesearch action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Imagesearch> byId = imageDao.findById(action.getId());
        if (byId.isPresent()){
            Imagesearch oldAction = imageDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                imageDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Imagesearch> findAll(){
        return imageDao.findAll();
    }

    public Imagesearch findById(String id){
        return imageDao.findById(id).get();
    }

    public List<Imagesearch> findByBq(String biaoqian,String name,String dates,Integer cloud){
        String[] arr = dates.split(",");
        try {
            List<Imagesearch> returnCommons = new ArrayList<>();
            List<Imagesearch> commons = imageDao.findByBiaoqianAndName(biaoqian,name,cloud);
            for (Imagesearch common:commons) {
                String dateStr = common.getYear();
                Date date = simpleDateFormat.parse(dateStr);
                Date start = simpleDateFormat.parse(arr[0]);
                Date end = simpleDateFormat.parse(arr[1]);

                System.out.println(dateStr + arr[0] + arr[1]);

                if (start.before(date) && end.after(date)){
                    System.out.println(dateStr + arr[0] + arr[1]);
                    returnCommons.add(common);
                }
            }
            return returnCommons;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(String id){
        try {
            imageDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
