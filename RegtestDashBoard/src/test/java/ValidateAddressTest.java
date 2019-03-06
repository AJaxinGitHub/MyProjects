import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;

/**
 * @author ajaxin
 * @discription TODO
 * @title RegtestDashBoard
 * @date 2019/2/21 - 13:35
 */
public class ValidateAddressTest {
    public static boolean isBTCValidAddress(String address) {
        try {
            NetworkParameters networkParameters = null;
            networkParameters = RegTestParams.get();
            Address validateAddr = Address.fromBase58(networkParameters, address);
            if (validateAddr != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

//    2N2fypk1aghxNx7dmFsNStoKannu4WEe7bv 为私链中有效地址
//    2N2fypk1aghxNx7dmFsNStoKannu4WEe7bb 为无效地址
    public static void main(String[] args) {
        System.out.println(isBTCValidAddress("2N2fypk1aghxNx7dmFsNStoKannu4WEe7bv"));
    }

}
