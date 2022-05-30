package br.com.test.ecommerce;

public class CustomerThreadLocal
{
    private static final ThreadLocal<Integer> userThreadLocal = new ThreadLocal<Integer>();

    public static void set(
        final Integer customerId )
    {
        userThreadLocal.set( customerId );
    }

    public static int get()
    {
        return userThreadLocal.get();
    }

    public static void remove()
    {
        userThreadLocal.remove();
    }
}