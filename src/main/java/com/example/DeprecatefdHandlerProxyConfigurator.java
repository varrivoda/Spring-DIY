package com.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class DeprecatefdHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIfNeeded(Object t, Class implClass) {

        if (implClass.isAnnotationPresent(Deprecated.class)) {

            if(implClass.getInterfaces().length==0) {
                return Enhancer.create(implClass,
                        (InvocationHandler) (o, method, args) -> getInvocationHandlerLogic(t, implClass, method, args));
            }

            return Proxy.newProxyInstance(implClass.getClassLoader(),
                    implClass.getInterfaces(),
                    (proxy, method, args) -> getInvocationHandlerLogic(t, implClass, method, args));

        }else {
            return t;
        }

    }

    private Object getInvocationHandlerLogic(Object t, Class implClass, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("**** ���� ����� '"+ implClass.getSimpleName()+"' is @Deprecated! ****");
        return method.invoke(t, args);
    }
}
