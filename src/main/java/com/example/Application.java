package com.example;

import java.util.HashMap;
import java.util.Map;

public class Application {
    static ApplicationContext run(String packagesToScan, Map<Class, Class> ifcToImplClass){
        Config config = new JavaConfig(packagesToScan, ifcToImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory factory = new ObjectFactory(context);

        //TODO homework:  init all singletones which are not lazy:
        // скан пакетов, поискать @Singletone, сразу посоздавать объекты, сразу засунуть их в Контекст
        context.setFactory(factory);
        return context;
    };
}
