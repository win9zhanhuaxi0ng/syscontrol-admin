package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.BookService;
import com.demofactory.syscontrol.domain.Books;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author : Hanamaru
 * @description: TODO
 * @date : 2020/8/18 12:02
 */
@RestController
@RequestMapping("management")
public class BookController {
    @Reference(check = false)
    private BookService bookService;

    @PostMapping("insertBook")
    public String insertBook(Books books){

        if(StringUtils.isEmpty(books.getBookName()))
        {
            System.out.println("result------书名不能为空");
            return "书名不为空";
        }

        return bookService.insertBook(books);
    }

    @PostMapping("selectBook")
    public List<Books> selectBook(Books books)
    {
        if(StringUtils.isEmpty(books.getBookName()))
        {
            System.out.println("result------书名不能为空");
            return null;
        }
        return bookService.selectBook(books);
    }
    @PostMapping("deleteBook")
    public String deleteBook(Books books)
    {
        if(StringUtils.isEmpty(books.getBookName()))
        {
            System.out.println("result------书名不能为空");
            return "书名不能为空";
        }
        return bookService.deleteBook(books);
    }
}
