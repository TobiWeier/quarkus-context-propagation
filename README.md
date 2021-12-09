# quarkus-context-propagation

Test with @RequestScoped ContextHolder in scenario with kafka message receiving.

Steps for reproducing

    cd quarkus-context-propagation
    docker-compose up -d
    mvn clean quarkus:dev

The cosole output shows, that both message receiving controllers use the same injected (@RequestScoped)
ContextHolder object. 

If this injected object is used for storing data during the request, the parallel message processing influences 
each other.
