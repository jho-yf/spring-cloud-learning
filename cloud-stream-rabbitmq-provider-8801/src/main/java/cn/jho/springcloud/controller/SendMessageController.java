package cn.jho.springcloud.controller;

import cn.jho.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-21 20:38
 */
@RestController
public class SendMessageController {

    private IMessageProvider messageProvider;

    public SendMessageController(IMessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }

}
