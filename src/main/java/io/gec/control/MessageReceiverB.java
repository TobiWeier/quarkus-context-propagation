package io.gec.control;

import io.smallrye.reactive.messaging.annotations.Blocking;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@RequestScoped
public class MessageReceiverB {
    
    @Inject
    ControllerB controllerB;
    @Inject
    ContextHolder context;
    
    @ClearContext
    @Incoming("iotos-test-topic-b")
    @Blocking
    @OnOverflow(OnOverflow.Strategy.LATEST)
    public void receiveMessage(String aMessage) {
        context.put("key1", "MessageReceiverB");
        try { Thread.sleep(100l); } catch (InterruptedException ie) {}
        System.out.println("MessageReceiverB: key1=" + context.get("key1") + " (context=" + context + ", thread=" + Thread.currentThread().getId() + ")");
        controllerB.rsInvoke();
    }
    
}
