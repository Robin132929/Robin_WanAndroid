package com.robin.robin_wanandroid.util.Login;

import com.robin.robin_wanandroid.annotation.CheckLogin;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


@Aspect
public class CheckLoginAspect {
    @Pointcut("execution(@com.robin.robin_wanandroid.annotation.CheckLogin * *(..))")
    public void checkLogin() {
    }

    @Around("checkLogin()")
    public void aroundLoginPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();

        if (!(signature instanceof MethodSignature)) {
            throw new Exception("annotation is only use in method");
        }

        MethodSignature methodSignature = (MethodSignature) signature;

        CheckLogin checkLogin = methodSignature.getMethod().getAnnotation(CheckLogin.class);
        if (checkLogin == null) {
            return;
        }


        if (LoginHelper.getInstance().getState().getType() == Offline.LOGOUT) {
            LoginHelper.getInstance().login();

            if (checkLogin.action() == CheckLogin.Action.JUMP) {
                LoginHelper.getInstance().addLinstener(new Linstener() {
                    @Override
                    public boolean LoginSuccess() {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                            return false;
                        }
                        return true;
                    }
                });
            }

        } else {
            joinPoint.proceed();
        }
    }


}
