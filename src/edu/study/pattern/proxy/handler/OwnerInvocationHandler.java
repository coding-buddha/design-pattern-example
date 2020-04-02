package edu.study.pattern.proxy.handler;


import edu.study.pattern.proxy.model.PersonBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OwnerInvocationHandler implements InvocationHandler {

    PersonBean person;

    public OwnerInvocationHandler(PersonBean person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try{
            /** getter() 의 경우는 [본인] 확인할 수 있다. **/
            if(method.getName().startsWith("get")){
                return method.invoke(person, args);
            }

            /** [본인은] 선호도 점수를 매길 수 없다. **/
            if(method.getName().equals("setHotOrNotRating")) {
                throw new IllegalArgumentException();
            }

            /** setter() 의 경우는 [본인만] 변경할 수 있다. **/
            if(method.getName().startsWith("set")) {
                return method.invoke(person, args);
            }
        } catch (InvocationTargetException e){
            e.printStackTrace();;
        }

        /** 그 이외의 메소드가 호출되는 경우 null **/
        return null;
    }
}
