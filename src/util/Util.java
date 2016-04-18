package util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class Util {

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            //do nothing
        }
    }

    public static void callStaticMethod(Class className, String methodName)
    {
    	//TODO
    	// Method API not really needed. Switch case is better.
        Method method;
        try
        {
            method = className.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(null);    //static method
            method.setAccessible(false);
        } 
        catch (NoSuchMethodException e) 
        {
            e.printStackTrace();
        } 
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } 
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
