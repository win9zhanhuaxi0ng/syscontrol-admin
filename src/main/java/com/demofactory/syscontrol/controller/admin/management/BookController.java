package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.BookService;
import com.demofactory.syscontrol.common.Result;
import com.demofactory.syscontrol.domain.Books;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("management")
public class BookController {
    @Reference(check = false)
    private BookService bookService;

    @PostMapping("insertBook")
    public Result insertBook(@RequestBody Books books) {
        if (StringUtils.isBlank(books.getBookName())) {
            log.info("result------书名不能为空");
            return Result.failure("书名不为空");
        }
        if (Objects.isNull(books.getDomainId())) {
            log.info("result------域不能为空");
            return Result.failure("域不能为空");
        }
        return bookService.insertBook(books);
    }

    @PostMapping("selectBook")
    public List<Books> selectBook(@RequestBody Books books) {
        return bookService.selectBook(books);
    }

    @PostMapping("deleteBook")
    public Result deleteBook(@RequestBody Books books) {
        return bookService.deleteBook(books.getId());
    }
}
