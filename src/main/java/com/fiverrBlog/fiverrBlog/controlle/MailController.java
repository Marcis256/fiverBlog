package com.fiverrBlog.fiverrBlog.controlle;

import com.fiverrBlog.fiverrBlog.model.MailStructure;
import com.fiverrBlog.fiverrBlog.service.Impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public String endMail(@RequestBody MailStructure mailStructure) {
        mailService.sendMail(mailStructure);
        return "Successfully sent the mail !!";
    }
}
