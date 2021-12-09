package io.gec.control;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class ControllerA {
    
    @Inject
    ContextHolder context;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void rsInvoke() {
        context.put("key2", "ControllerA");
        try { Thread.sleep(100l); } catch (InterruptedException ie) {}
        System.out.println("ControllerA: key1=" + context.get("key1") + ",  key2=" + context.get("key2") + " (context=" + context + ", thread=" + Thread.currentThread().getId() + ")");
    }
    
}
