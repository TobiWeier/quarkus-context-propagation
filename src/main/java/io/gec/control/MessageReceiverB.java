package io.gec.control;

import io.smallrye.reactive.messaging.annotations.Blocking;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@RequestScoped
public class MessageReceiverB {
    
    @Inject
    ControllerB controllerB;
    @Inject
    ContextHolder context;
    
    @Incoming("iotos-test-topic-b")
    @Blocking
    @OnOverflow(OnOverflow.Strategy.LATEST)
    public void receiveMessage(String aMessage) {
        process(aMessage);
    }
    
    @Transactional
    @ActivateRequestContext
    public void process(String aMessage) {
        context.put("key1", "MessageReceiverB");
        try { Thread.sleep(100l); } catch (InterruptedException ie) {}
        System.out.println("MessageReceiverB: key1=" + context.get("key1") + " (context=" + context + ", thread=" + Thread.currentThread().getId() + ")");
        controllerB.rsInvoke();
    }
    
}
