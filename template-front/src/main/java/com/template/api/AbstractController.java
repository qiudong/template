package com.template.api;

import com.template.api.common.ResponseMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: qiudong
 * @date: 2018/7/23
 * @version: V1.0
 * @review: qiudong /2018/7/23
 */
@Slf4j
public class AbstractController {

    /**
     * 不带分页的调用 有参数 有返回值的调用
     *
     * @param function
     * @param params
     * @param <T>
     * @param <R>
     * @return
     */
    public <T, R> ResponseMessage wrapper(MyFunction<T, R> function, T params) {
        try {
            R r = function.apply(params);
            return ResponseMessage.ok("成功", r);
        } catch (Exception e) {
            return ResponseMessage.error("系统错误");
        }
    }

    /***
     * 有参数 无返回值
     * @param consumer
     * @param params
     * @param <T>
     * @return
     */
    public <T> ResponseMessage wrapperConsumer(MyConsumer<T> consumer, T params) {
        try {
            consumer.accept(params);
            return ResponseMessage.ok();
        } catch (Exception e) {
            return ResponseMessage.error("系统错误");
        }
    }

    /***
     * 无参数 有返回值
     * @param supplier
     * @return
     */
    public ResponseMessage wrapperSupplier(MySupplier supplier) {
        try {
            Object t = supplier.get();
            return ResponseMessage.ok(t);
        } catch (Exception e) {
            log.info("系统异常");
            return ResponseMessage.error("系统错误");
        }
    }

    @FunctionalInterface
    public interface MySupplier<T> {
        T get() throws Exception;
    }

    @FunctionalInterface
    public interface MyConsumer<T> {
        void accept(T t) throws Exception;
    }

    @FunctionalInterface
    public interface MyFunction<T, R> {
        R apply(T t) throws Exception;
    }


}
