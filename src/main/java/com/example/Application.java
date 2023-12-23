package com.example;

import java.util.HashMap;
import java.util.Map;

public class Application {
    static ApplicationContext run(String packagesToScan, Map<Class, Class> ifcToImplClass){
        Config config = new JavaConfig(packagesToScan, ifcToImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory factory = new ObjectFactory(context);

        //TODO homework:  init all singletones which are not lazy:
        // ���� �������, �������� @Singletone, ����� ����������� �������, ����� �������� �� � ��������
        context.setFactory(factory);
        return context;
    };
}
