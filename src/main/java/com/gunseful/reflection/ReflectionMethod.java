package com.gunseful.reflection;

import java.lang.reflect.Field;

public class ReflectionMethod {
    public void pars(String tag, Object o, String tagData) {
        //создамем поле, и присваиеваем ноль
        Field field = null;
        try {
            //пробуем найти соответствие поля из переданного объекта и имени тега и присваиваем филду найденное поле
            field = o.getClass().getDeclaredField(tag);
            //даем доступ к полю
            field.setAccessible(true);
            //определяем тип поля
            Class<?> fieldType = field.getType();
            //если тип поля string, устанавливаем значение из тэга в значение поля
            if (fieldType.equals(String.class)) {
                field.set(o, (String) tagData);
            }
            //если тип данный integer, приводим строку к integer и устанавливаем в значение поля
            if (fieldType.equals(int.class)) {
                Integer i = Integer.parseInt(tagData);
                field.set(o, i);
            }
            //если тип данный double, приводим строку к intdoubleeger и устанавливаем в значение поля
            if (fieldType.equals(double.class)) {
                Double i = Double.parseDouble(tagData);
                field.set(o, i);
            }
            //если тип поля boolean, мы проверяем на камень на драгоценность, если указано что он драгоценный, то устанавливаем значение поля - true
            if (fieldType.equals(boolean.class)) {
                if (tagData.equals("precious")) {
                    field.set(o, true);
                }
            }
            //все исключения пропускаем
        } catch (Exception ignored) {
        }
    }
}

