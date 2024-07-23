package ru.logs.log;

import ru.logs.entity.Model;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Utils {
    public static <T> T cache (T objectIncome) {
        return (T) Proxy.newProxyInstance(
                objectIncome.getClass().getClassLoader(),
                objectIncome.getClass().getInterfaces(),
                new CacheClass(objectIncome)
        );
    }
}
class CacheClass implements InvocationHandler {
    private Object object;

    public CacheClass(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String logFileName = this.object.getClass().getDeclaredAnnotation(LogTransformation.class).value(); // получим имя лог-файла
        String arrArgs = Arrays.toString(args);   // получим список параметров
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // текущее дата и время

        Object retValueMethod = method.invoke(object, args); // выполним операцию

        // запишем информацию в файл
        try(FileWriter writer = new FileWriter(logFileName, true)) {
            writer.write("Дата и время начала выполнение операции:" + nowTime + "\n");
            writer.write("Название класса: "+ this.object.getClass().getName() + "\n");
            writer.write("Название операции: "+ method.getName() + "\n");
            writer.write("Входные параметры: " + arrArgs + "\n");
            writer.write("Возвращаемое значение: " + retValueMethod + "\n");
            writer.flush();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return retValueMethod;
    }
}