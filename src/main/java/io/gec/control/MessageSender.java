package io.gec.control;

import io.quarkus.scheduler.Scheduled;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@RequestScoped
public class MessageSender {

    @Inject
    @Channel("iotos-test-topic-a")
    @OnOverflow(OnOverflow.Strategy.UNBOUNDED_BUFFER)
    Emitter<String> emitterA;
    
    @Inject
    @Channel("iotos-test-topic-b")
    @OnOverflow(OnOverflow.Strategy.UNBOUNDED_BUFFER)
    Emitter<String> emitterB;
    
    @Scheduled(cron = "*/5 * * ? * * *")
    public void sendMessageA() {
        String msg  = UUID.randomUUID().toString();
        emitterA.send(msg);
    }

    @Scheduled(cron = "*/5 * * ? * * *")
    public void sendMessageB() {
        String msg  = UUID.randomUUID().toString();
        emitterB.send(msg);
    }

}
