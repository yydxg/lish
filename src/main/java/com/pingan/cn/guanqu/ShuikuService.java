package com.pingan.cn.guanqu;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("shuikuService")
public class ShuikuService {
    @Autowired
    private ShuikuDao actionDao;

    @Autowired
    private ShuiweiService shuiweiService;

    public Shuiku saveAction(Shuiku person){
        Shuiku save = null;
        try {
            return actionDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Shuiku action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Shuiku> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Shuiku oldAction = actionDao.findById(action.getId()).get();
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

    public List<Shuiku> findAll(){
        return actionDao.findAll();
    }

    public List<ShuikuVo> findAllWithShuiwei(){
        List<Shuiku> lists = actionDao.findAll();
        List<ShuikuVo> result = new ArrayList<>();
        for (int i=0;i<lists.size();i++){
            String num = lists.get(i).getNum();
            Shuiwei shui = shuiweiService.findBySTNum(num);

            Shuiku tangba = lists.get(i);
            ShuikuVo vo = new ShuikuVo();
            vo.setShuiwei(shui);
            vo.setArea(tangba.getArea());
            vo.setGate(tangba.getGate());
            vo.setId(tangba.getId());
            vo.setMax_water(tangba.getMax_water());
            vo.setName(tangba.getName());
            vo.setNum(tangba.getNum());
            vo.setPosition(tangba.getPosition());

            result.add(vo);
        }
        return result;
    }


    public Shuiku findById(String id){
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
        ArrayList<Shuiku> arrayList = new ArrayList<>();
        for (String id:ids) {
            Shuiku entity = findById(id);
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
