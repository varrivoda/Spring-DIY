package com.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class DeprecatefdHandlerProxyConfigurator implements ProxyConfigurator {

    List<Method> deprecatedMethods = new ArrayList<>();
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
            // теперь займемся методами
            for (Method method : implClass.getMethods()) {
                if(method.isAnnotationPresent(Deprecated.class)){
                    deprecatedMethods.add(method);
                }
            }

            if(deprecatedMethods.isEmpty()){
                return t;
            }else{
                return Enhancer.create(implClass, (InvocationHandler) (o, method, args) -> {
                    if(method.isAnnotationPresent(Deprecated.class)){
                        System.out.println("**this method " + method.getName() + "() is Deprecated!**");
                    }
                    return method.invoke(t, args);
                });
            }
        }

    }

    private Object getInvocationHandlerLogic(Object t, Class implClass, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("**** Весь этот класс '"+ implClass.getSimpleName()+"' is @Deprecated! ****");
        return method.invoke(t, args);
    }
}
