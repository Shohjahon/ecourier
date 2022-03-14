package uz.logistics.ecourier.common;

@Generated
public class ThreadLocalSingleton {
    private final static ThreadLocalSingleton INSTANCE = new ThreadLocalSingleton();
    private final static ThreadLocal<String> MESSAGE = ThreadLocal.withInitial(String::new);

    private ThreadLocalSingleton(){

    }

    public static ThreadLocalSingleton getInstance(){
        return INSTANCE;
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
