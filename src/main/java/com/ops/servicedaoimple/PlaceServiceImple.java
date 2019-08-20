package com.ops.servicedaoimple;

import com.ops.dao.PlaceMapper;
import com.ops.dao.UseMapper;
import com.ops.dao.WxUserBackPackMapper;
import com.ops.dao.WxUserMapper;
import com.ops.entity.Place;
import com.ops.entity.Use;
import com.ops.entity.WxUserBackPack;
import com.ops.servicedao.PlaceService;
import com.ops.sunwou.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlaceServiceImple implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private WxUserBackPackMapper wxUserBackPackMapper;
    @Autowired
    private UseMapper useMapper;

    @Override
    public List<Place> find(Place place) {
        return placeMapper.find(place);
    }

    @Override
    public List<Place> findByCon(String userId) {
        return placeMapper.findByCon(userId);
    }

    @Override
    public void updateName(Place place) {
        Place p = placeMapper.selectByPrimaryKey(place.getId());
        if (p.getUpdateCount() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", place.getUserid());
            map.put("amount", new BigDecimal(80));
            int payResult = wxUserMapper.payMoney(map);
            if (payResult == 1) {
                placeMapper.updateByPrimaryKeySelective(place);
            } else {
                throw new MyException("余额不足");
            }
        } else {
            place.setUpdateCount(1);
            placeMapper.updateByPrimaryKeySelective(place);
        }
    }

    @Override
    public Place findById(String id) {
        return placeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void bz(String[] ids, int[] count, Use use) {
        String content = "";
        for (int i = 0; i < ids.length; i++) {
            WxUserBackPack pack = new WxUserBackPack();
            pack.setId(ids[i]);
            pack.setCount(count[i]);
            if (wxUserBackPackMapper.use(pack) == 1) {
                WxUserBackPack temp = wxUserBackPackMapper.selectByPrimaryKey(ids[i]);
                content += temp.getName() + ":" + count[i] + ",";
            } else {
                throw new MyException("服务器错误");
            }
            use.setContent(content);
            useMapper.insert(use);
        }
    }

    @Override
    public void bzupdate(Use use) {
        useMapper.updateByPrimaryKeySelective(use);
    }

    @Override
    public List<Use> findbz(Use use) {
        return useMapper.find(use);
    }

    @Override
    public List<Place> findByUserId(String openid) {
        return placeMapper.findUserId(openid);
    }

    @Override
    public int update(Place place) {
        return placeMapper.updateByPrimaryKeySelective(place);
    }
}
