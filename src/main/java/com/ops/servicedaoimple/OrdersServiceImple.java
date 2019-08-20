package com.ops.servicedaoimple;

import com.google.gson.JsonObject;
import com.ops.controller.WxUserController;
import com.ops.dao.*;
import com.ops.entity.*;
import com.ops.servicedao.OrdersService;
import com.ops.sunwou.exception.MyException;
import com.ops.sunwou.wx.NotifyImple;
import com.ops.sunwou.wx.WXpayUtil;
import com.ops.util.LoggerUtil;
import com.ops.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrdersServiceImple implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private WxUserCouponMapper wxUserCouponMapper;
    @Autowired
    private WxUserBackPackMapper wxUserBackPackMapper;

    static JsonObject chargecall = new JsonObject();

    static {
        chargecall.addProperty("callback", "charge");
        chargecall.addProperty("attach", "");
        LoggerUtil.log("@ops");
    }

    @Transactional
    @Override
    public String add(Integer[] productIds, Integer[] numbers, Orders orders) {
        wxUserMapper.updateAddress(new WxUser(orders.getUserid(), orders.getAddressName()
                + "," + orders.getAddressPhone() + "," + orders.getAddress()));
        String orderId = Util.GenerateOrderNumber(orders.getUserid(), UUID.randomUUID().toString().substring(0, 5));
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < productIds.length; i++) {
            Product product = productMapper.selectByPrimaryKey(productIds[i]);
            if (i == 0) {
                orders.setShopid(product.getShopid());
                if (product.getType().equals("农作物")) {
                    orders.setType("摆摊订单");
                } else {
                    orders.setType("基地订单");
                }
            }
            if (product.getStock() > 0) {
                OrderProduct op = new OrderProduct(product, numbers[i], orderId);
                orderProductMapper.insert(op);
                total = total.add(op.getTotalprice());
            } else {
                throw new MyException(product.getName() + "库存不足");
            }
        }
        orders = orders.set(orderId, total);
        //使用优惠券
        if (orders.getCouponid() != 0 && orders.getType().equals("基地订单")) {
            WxUserCoupon wuc = wxUserCouponMapper.selectByPrimaryKey(orders.getCouponid());
            if (wuc != null) {
                Coupon coupon = couponMapper.selectByPrimaryKey(wuc.getCouponid());
                if (orders.getTotalprice().intValue() >= coupon.getFull()) {
                    orders.setTotalprice(orders.getTotalprice().subtract(new BigDecimal(coupon.getReduce())));
                    orders.setCouponprice(new BigDecimal(coupon.getReduce()));
                    wuc.setOrderid(orders.getId());
                    int rs = wxUserCouponMapper.use(wuc);
                    if (rs != 1) {
                        throw new MyException("优惠券无法使用");
                    }
                } else {
                    throw new MyException("优惠券无法使用");
                }
            } else {
                throw new MyException("优惠券不存在");
            }
        }
        ordersMapper.insert(orders);
        return orderId;
    }

    @Transactional
    @Override
    public Object charge(Orders order, String addr) {
        Object obj = WXpayUtil.payrequest(WxUserController.app.get("appid"), WxUserController.app.get("mch_id"),
                WxUserController.app.get("paykey"), "一块地-w", order.getId(),
                order.getTotalprice().multiply(new BigDecimal(100)).intValue() + "", order.getUserid(), addr,
                chargecall, "https://www.sunwou.com/oneplace/notify", new NotifyImple() {
                    @Transactional
                    @Override
                    public boolean notifcation(Map<String, String> map) {
                        Orders pt = ordersMapper.selectByPrimaryKey(map.get("out_trade_no"));
                        pt.setStatus("已完成");
                        Map<String, Object> oo = new HashMap<>();
                        oo.put("id", map.get("out_trade_no"));
                        oo.put("status", "已付款");
                        oo.put("statusold", "待付款");
                        if (ordersMapper.updateStatus(oo) == 1) {
                            Map<String, Object> mo = new HashMap<>();
                            LoggerUtil.log("@ops:" + pt.getUserid() + "," + pt.getTotalprice().toString());
                            mo.put("userId", pt.getUserid());
                            mo.put("amount", pt.getTotalprice());
                            if (wxUserMapper.addmoney(mo) == 1) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                });
        ordersMapper.insert(order);
        return obj;
    }

    @Transactional
    @Override
    public String pay(String userId, String orderId) {
        String id = "";
        Orders orders = ordersMapper.selectByPrimaryKey(orderId);
        List<Product> di = new ArrayList<>();
        List<OrderProduct> op;
        if (orders.getStatus().equals("待付款") && userId.equals(orders.getUserid())) {
            Map<String, Object> mo = new HashMap<>();
            mo.put("userId", orders.getUserid());
            mo.put("amount", orders.getTotalprice());
            if (wxUserMapper.payMoney(mo) == 1) {
                orders.setStatus("已付款");
                op = orderProductMapper.findByOrder(orders.getId());
                for (OrderProduct temp : op) {
                    if (temp.getProducttype().equals("地")) {
                        for (int i = 0; i < temp.getNumbers(); i++)
                            di.add(productMapper.selectByPrimaryKey(temp.getProductId()));
                    }
                    if (temp.getProducttype().equals("种子")) {
                        WxUserBackPack kc = new WxUserBackPack(orders.getUserid() + "-" + temp.getId(),
                                orders.getUserid(), temp.getProductimage(), temp.getProductname(), temp.getNumbers());
                        if (wxUserBackPackMapper.selectByPrimaryKey(orders.getUserid() + "-" + temp.getId()) == null) {
                            wxUserBackPackMapper.insert(kc);
                        } else {
                            wxUserBackPackMapper.add(kc);
                        }
                    }
                    // 库存减少和销量的增加
                    Map<String, Object> so = new HashMap<>();
                    so.put("id", temp.getProductId());
                    so.put("count", temp.getNumbers());
                    if (productMapper.sale(so) != 1) {
                        throw new MyException("商品库存不足");
                    }
                }
                Map<String, Object> oo = new HashMap<>();
                oo.put("id", orders.getId());
                oo.put("status", "已付款");
                oo.put("statusold", "待付款");
                if (ordersMapper.updateStatus(oo) == 1) {
                    for (Product p : di) {
                        Place place = new Place(UUID.randomUUID().toString(), p.getUrl(), p.getUrlImage(),
                                orders.getUserid(), p.getShopid(), new Date().getTime());
                        place.setProductName(p.getName());
                        placeMapper.insert(place);
                        id = place.getId();
                    }
                } else {
                    throw new MyException("订单状态异常");
                }
            } else {
                throw new MyException("余额不足");
            }
        }
        return id;
    }

    @Override
    public List<Orders> find(Orders orders) {
        return ordersMapper.find(orders);
    }

}
