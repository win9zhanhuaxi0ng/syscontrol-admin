package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.BookService;
import com.demofactory.syscontrol.domain.Books;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Objects;

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

        if(StringUtils.isBlank(books.getBookName()))
        {
            System.out.println("result------书名不能为空");
            return "书名不为空";
        }
        if(Objects.isNull(books.getDomainId()))
        {
            System.out.println("result------域不能为空");
            return "域不能为空";
        }
        return bookService.insertBook(books);
    }

    @PostMapping("selectBook")
    public List<Books> selectBook(Books books)
    {
        return bookService.selectBook(books);
    }
    @PostMapping("deleteBook")
    public String deleteBook(Books books)
    {
        if(StringUtils.isBlank(books.getBookName()))
        {
            System.out.println("result------书名不能为空");
            return "书名不能为空";
        }
        if(Objects.isNull(books.getDomainId()))
        {
            System.out.println("result------域不能为空");
            return null;
        }
        return bookService.deleteBook(books.getId());
    }
}
