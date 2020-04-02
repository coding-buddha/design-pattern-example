package edu.study.pattern.proxy.handler;

import edu.study.pattern.proxy.model.PersonBean;

import java.lang.reflect.Proxy;

public class CustomProxy {

    PersonBean getOwnerProxy(PersonBean person) {

        /** 프록시 생성 **/
        return (PersonBean) Proxy.newProxyInstance(
            person.getClass().getClassLoader(),     // (1) person 클래스 로더 전달
            person.getClass().getInterfaces(),      // (2) 프록시에 구현해야하는 인터페이스도 인자로 전달
            new OwnerInvocationHandler(person)      // (3) 호출 핸들러인 OwnerInvocationHandler 도 인자로 전달
        );
    }

    PersonBean getNonOwnerProxy(PersonBean person) {
        return (PersonBean) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person)
        );
    }

//    Proxy getProxy(InvocationHandler invocationHandler, PersonBean person) {
//        return (CustomProxy) Proxy.newProxyInstance(
//                invocationHandler.getClass().getClassLoader(),
//                invocationHandler.getClass().getInterfaces(),
//                new InvocationHandler(person)
//        );
//    }
}
