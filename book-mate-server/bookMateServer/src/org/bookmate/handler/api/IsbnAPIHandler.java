package org.bookmate.handler.api;

import org.apache.commons.lang.StringUtils;
import org.bookmate.entities.Isbn;
import org.bookmate.entities.User;
import org.bookmate.service.IsbnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Tiger
 * @Date: 2020/1/11 15:59
 * @Description:
 */
@Controller
public class IsbnAPIHandler {

    @Autowired
    IsbnService service;


    @ResponseBody
    @RequestMapping(value = "api-isbn-add-isbn")
    public int inserIsbn(@RequestParam("isbn") String isbn, @RequestParam("bookName") String bookName,
                         @RequestParam("userid") Integer userid) {
        if (StringUtils.isEmpty(isbn)) {
            return 0;
        }
        Isbn isbnB = new Isbn();
        isbnB.setIsbn(isbn);
        isbnB.setBookName(bookName);
        isbnB.setUserid(userid);
        try {
            service.insertIsbn(isbnB);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @ResponseBody
    @RequestMapping(value = "api-isbn-findmy-isbn/{userid}", method = RequestMethod.GET)
    public List<Isbn> inserIsbn(@PathVariable String userid) {
        return service.findByUserid(userid);
    }
}
