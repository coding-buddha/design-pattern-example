package edu.study.pattern.proxy.handler;

import edu.study.pattern.proxy.model.PersonBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NonOwnerInvocationHandler implements InvocationHandler {

    PersonBean person;

    public NonOwnerInvocationHandler(PersonBean person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try {
            /** getter() 의 경우는 [타인] 확인할 수 있다. **/
            if(method.getName().startsWith("get")) {
                return method.invoke(person, args);
            }

            /** [타인은] 선호도 점수를 매길 수 없다. setter() 의 경우 [타인은] 변경할 수 없다. **/
            if(method.getName().equals("setHotOrNotRating")
                    || method.getName().startsWith("set")) {
                throw new IllegalArgumentException();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
