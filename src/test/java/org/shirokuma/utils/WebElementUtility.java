package org.shirokuma.utils;

import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class WebElementUtility {

    public static By getLocator(Object obj, String fieldName) {
        Field field;
        try {
            field = obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        By by;

        try {
            by = (By) field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return by;
    }

}
