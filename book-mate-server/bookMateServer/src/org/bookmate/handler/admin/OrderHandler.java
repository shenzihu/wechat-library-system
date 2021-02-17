package org.bookmate.handler.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bookmate.entities.Order;
import org.bookmate.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 预定相关控制器
 *
 * @author Tiger
 */
@Controller
public class OrderHandler {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "admin-order-list-show", method = RequestMethod.GET)
    public String listShow(Map<String, Object> requestMap, @RequestParam("page") Integer page, @RequestParam("status") Integer status) {
        requestMap.put("nav", "order");
        List<Order> orders =new ArrayList<>();
        if (status == 4){
            orders =  orderService.getAllOrder();
        }else {
            orders = orderService.getOrderByStatus(status);
        }
        requestMap.put("orders", orders);

        int pageCount = orders.size();  //数据条数
        int pageSize = 50;  //分页条数
        int pageMax = pageCount / pageSize;  //最大页数
        int pagePointer = 1;  //当前指向页
        if (pageMax != 0 && pageCount % pageSize != 0) {
            ++pageMax;
        }
        if (pageMax == 0) {
            pageMax = 1;
        }
        if (page < 1 || page > pageMax) {
            pagePointer = 1;
        } else {
            pagePointer = page;
        }
        ArrayList<Order> pageOrders = new ArrayList<>();
        if (pageMax == 1) {
            pageOrders.addAll(orders);
        } else if (pagePointer == pageMax) {
            int tmp = pageCount % pageSize;
            if (tmp == 0) {
                tmp = pageSize;
            }
            for (int i = pageSize * (pagePointer - 1); i < pageSize * (pagePointer - 1) + tmp; ++i) {
                pageOrders.add(orders.get(i));
            }
        } else {
            for (int i = pageSize * (pagePointer - 1); i < pageSize * pagePointer; ++i) {
                pageOrders.add(orders.get(i));
            }
        }
        requestMap.put("pageMax", pageMax);
        requestMap.put("pagePoint", pagePointer);
        requestMap.put("pageOrders", pageOrders);
        requestMap.put("status", status);

        return "order/order_list";
    }

    @RequestMapping(value = "admin-order-check-show", method = RequestMethod.GET)
    public String listShow(String id, String status) {
        orderService.adminCheckOrder(Integer.parseInt(id), Integer.parseInt(status));
        return "redirect:admin-order-list-show?page=1&status="+status;
    }

    /**
     * 显示编辑图书信息
     *
     * @return
     */
    @RequestMapping(value = "admin-order-edit-show/{id}", method = RequestMethod.GET)
    public String editBookShow(@PathVariable("id") Integer id, Map<String, Object> requestMap) {
        Order order = orderService.getOrderById(id);
        requestMap.put("order", order);
        System.out.println(order);
        return "order/order_edit";
    }

    @RequestMapping(value = "admin-order-edit-execute", method = RequestMethod.POST)
    public String editBookExecute(@RequestParam("num") Integer num, @RequestParam("status") Integer status, @RequestParam("id") Integer id) {
        Order order = orderService.getOrderById(id);
        order.setStatus(status);
        order.setNum(num);
        orderService.adminUpdateOrder(order);
        return "redirect:/admin-order-list-show?page=1&status="+4;
    }

    /**
     * 搜索订单显示
     *
     * @return order/order_list.jsp
     */
    @RequestMapping(value = "admin-order-searchorder-show", method = RequestMethod.GET)
    public String searchBookShow(Map<String, Object> requestMap, @RequestParam("content") String content) {
        requestMap.put("nav", "order");
        ArrayList<Order> orders = (ArrayList<Order>) orderService.seachOrder(content);
        requestMap.put("pageOrders", orders);
        return "order/order_list";
    }

    @RequestMapping(value = "admin-order-check-all-pass", method = RequestMethod.POST)
    @ResponseBody
    public String checkAllPass(@RequestBody List<Integer> ids) {
        if(ids != null){
            ids.forEach(x->orderService.adminCheckOrder(x,1));
        }
        return "成功";
    }
}

