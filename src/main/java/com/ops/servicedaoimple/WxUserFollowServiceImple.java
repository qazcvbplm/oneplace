package com.ops.servicedaoimple;

import com.ops.dao.WxUserFollowMapper;
import com.ops.entity.WxUserFollow;
import com.ops.servicedao.WxUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxUserFollowServiceImple implements WxUserFollowService {

    @Autowired
    private WxUserFollowMapper wxUserFollowMapper;

    @Override
    public boolean add(WxUserFollow wuf) {
        int count = wxUserFollowMapper.check(wuf);
        if (count == 1) {
            wxUserFollowMapper.remove(wuf);
            return false;
        } else {
            wxUserFollowMapper.insert(wuf);
            return true;
        }
    }


}
