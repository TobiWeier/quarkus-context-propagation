package io.gec.control;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ControllerB {
    
    @Inject
    ContextHolder context;
    
    public void rsInvoke() {
        context.put("key2", "ControllerB");
        try { Thread.sleep(100l); } catch (InterruptedException ie) {}
        System.out.println("ControllerB: key1=" + context.get("key1") + ",  key2=" + context.get("key2") + " (context=" + context + ", thread=" + Thread.currentThread().getId() + ")");
    }
    
}
