package com.ops.servicedaoimple;

import com.ops.dao.ActivityBDao;
import com.ops.entity.ActivityB;
import com.ops.servicedao.ActivityBService;
import ops.model.X.base.service.OpsServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ActivityBServiceImpl extends OpsServiceImpl<ActivityBDao, ActivityB> implements ActivityBService {
}
