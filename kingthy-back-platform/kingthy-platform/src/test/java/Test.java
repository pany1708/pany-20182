import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author 潘勇
 * @Data 2017/7/21 14:25.
 */
public class Test
{
    public static void main(String[] args)
        throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>()
        {
            @Override
            public String get()
            {
                try
                {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.printf("#####>>>" + Thread.currentThread().getName() + "\r\n");
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return "hello";
            }
        }, executor);
        System.out.printf(String.valueOf(resultCompletableFuture.thenAccept(new Consumer<String>()
        {
            @Override
            public void accept(String s)
            {
                System.out.printf("===>>" + s + "\r\n");
                System.out.printf("--->>>" + Thread.currentThread().getName() + "\r\n");
            }
        })));
        System.out.printf("123");
    }
}
