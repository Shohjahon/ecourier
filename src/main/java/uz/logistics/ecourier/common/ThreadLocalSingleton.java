package uz.logistics.ecourier.common;

public class ThreadLocalSingleton {
    private final static ThreadLocalSingleton INSTANCE = new ThreadLocalSingleton();
    private final static ThreadLocal<String> MESSAGE = ThreadLocal.withInitial(String::new);
    private final static ThreadLocal<Integer> COUNTER = ThreadLocal.withInitial(() -> 0);

    private ThreadLocalSingleton(){

    }

    public static ThreadLocalSingleton getInstance(){
        return INSTANCE;
    }

    public static Integer increment(){
        int counter = ThreadLocalSingleton.COUNTER.get();
        ThreadLocalSingleton.COUNTER.set(++counter);
        return ThreadLocalSingleton.COUNTER.get();
    }

    public static Integer getCounter(){
        return ThreadLocalSingleton.COUNTER.get();
    }

    public static String getMessage(){
        return ThreadLocalSingleton.MESSAGE.get();
    }

    public static void setMessage(String message){
        ThreadLocalSingleton.MESSAGE.set(message);
    }

    public static void clear(){
        ThreadLocalSingleton.MESSAGE.remove();
    }
}
