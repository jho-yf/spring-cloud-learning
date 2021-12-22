package cn.jho.springcloud.service.impl;

import cn.jho.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-21 20:31
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    /**
     * 消息发送管道
     */
    private final MessageChannel output;

    public MessageProviderImpl(MessageChannel output) {
        this.output = output;
    }

    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        log.info("uuid={}", uuid);
        return null;
    }
}
