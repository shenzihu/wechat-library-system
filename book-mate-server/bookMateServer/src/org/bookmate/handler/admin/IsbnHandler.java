package org.bookmate.handler.admin;

import org.bookmate.entities.Isbn;
import org.bookmate.service.IsbnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * isbn处理类
 *
 * @Author: Tiger
 * @Date: 2020/1/11 16:21
 * @Description:
 */
@Controller
public class IsbnHandler {

    @Autowired
    IsbnService isbnService;

    @RequestMapping(value = "admin-isbn-list-show", method = RequestMethod.GET)
    public String getAllIsbn(Map<String, Object> requestMap,String status) {
        requestMap.put("nav", "isbn");
        List<Isbn> isbns = new ArrayList<>();
        if ("all".equals(status)) {
            isbns.addAll(isbnService.findAllIsbnByStatus(0));
            isbns.addAll(isbnService.findAllIsbnByStatus(1));
        } else {
            isbns.addAll(isbnService.findAllIsbnByStatus(Integer.parseInt(status)));
        }
        requestMap.put("isbn", isbns);
        return "isbn/isbn_list";
    }
}
