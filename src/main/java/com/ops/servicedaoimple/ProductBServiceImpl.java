package com.ops.servicedaoimple;

import com.ops.dao.ProductBDao;
import com.ops.entity.ProductB;
import com.ops.servicedao.ProductBService;
import ops.model.X.base.service.OpsServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductBServiceImpl extends OpsServiceImpl<ProductBDao, ProductB> implements ProductBService {
}
