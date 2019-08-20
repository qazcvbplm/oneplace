package com.ops.servicedaoimple;

import com.ops.dao.WxUserBackPackMapper;
import com.ops.dao.WxUserMapper;
import com.ops.entity.WxUser;
import com.ops.entity.WxUserBackPack;
import com.ops.servicedao.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WxUserServiceImple implements WxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private WxUserBackPackMapper wxUserBackPackMapper;

    @Override
    public WxUser login(String openid) {
        WxUser wxUser;
        if ((wxUser = wxUserMapper.selectByPrimaryKey(openid)) == null) {
            wxUser = new WxUser();
            wxUser.setOpenid(openid);
            wxUserMapper.insert(wxUser);
            return wxUserMapper.selectByPrimaryKey(openid);
        } else {
            return wxUser;
        }
    }

    @Override
    public WxUser update(WxUser wxUser) {
        wxUserMapper.updateByPrimaryKeySelective(wxUser);
        return wxUserMapper.selectByPrimaryKey(wxUser.getOpenid());
    }

    @Override
    public List<WxUser> find(WxUser wxUser) {
        return wxUserMapper.find(wxUser);
    }

    @Override
    public List<WxUserBackPack> findzz(String userId) {
        // TODO Auto-generated method stub
        return wxUserBackPackMapper.findByuserId(userId);
    }


}
