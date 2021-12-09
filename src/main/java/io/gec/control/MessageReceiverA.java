package io.gec.control;

import io.smallrye.reactive.messaging.annotations.Blocking;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@RequestScoped
public class MessageReceiverA {

    @Inject
    ControllerA controllerA;
    @Inject
    ContextHolder context;

    @Incoming("iotos-test-topic-a")
    @Blocking
    @OnOverflow(OnOverflow.Strategy.LATEST)
    public void receiveMessage(String aMessage) {
        process(aMessage);
    }
    
    @ActivateRequestContext
    @Transactional
    public void process(String aMessage) {
        context.put("key1", "MessageReceiverA");
        try { Thread.sleep(100l); } catch (InterruptedException ie) {}
        System.out.println("MessageReceiverA: key1=" + context.get("key1") + " (context=" + context + ", thread=" + Thread.currentThread().getId() + ")");
        controllerA.rsInvoke();
    }
    
}
