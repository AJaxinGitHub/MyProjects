import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ajaxin
 * @discription TODO
 * @title RegtestDashBoard
 * @date 2019/2/20 - 13:17
 */
public class IPTest {
    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}
