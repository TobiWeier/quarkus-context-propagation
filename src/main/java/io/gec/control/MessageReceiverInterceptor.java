package io.gec.control;

import io.quarkus.arc.Arc;
import io.quarkus.arc.ManagedContext;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@ClearContext
@Interceptor
@Priority(0)
public class MessageReceiverInterceptor {

    @AroundInvoke
    Object intercept(InvocationContext context) throws Exception {
        final ManagedContext managedContext = Arc.container().requestContext();
        managedContext.deactivate();
        managedContext.activate();
        return context.proceed();
    }
}
