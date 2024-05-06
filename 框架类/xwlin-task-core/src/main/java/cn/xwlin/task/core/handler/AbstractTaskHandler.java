package cn.xwlin.task.core.handler;

import cn.xwlin.task.core.common.TaskHeaderCtx;

public abstract class AbstractTaskHandler {
//    private final Class<T> paramsClass;

    public AbstractTaskHandler() {
//        Type t = this.getClass().getGenericSuperclass();
//        for (Class superClass = this.getClass(); t instanceof Class; t = superClass.getGenericSuperclass()) {
//            superClass = superClass.getSuperclass();
//        }
//        Type[] actualTypeArguments = ((ParameterizedType) t).getActualTypeArguments();
//        this.paramsClass = (Class) actualTypeArguments[0];

        String name = this.getClass().getName();
        ClientHanderRegister.addHandler(name, this);
    }

    public abstract void execute(String param, TaskHeaderCtx taskHeaderCtx);
}
