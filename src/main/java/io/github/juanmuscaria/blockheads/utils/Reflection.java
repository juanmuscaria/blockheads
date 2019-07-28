package io.github.juanmuscaria.blockheads.utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class Reflection {
    public void loadJar(File jar) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class<?> clazz = cl.getClass();
        Method method = clazz.getSuperclass().getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(cl, jar.toURI().toURL());
    }
}
