import com.wi.core.server.NettyServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class WiStarter {
    public static void main(String[] args) {
        new NettyServer(8842);
    }
}
