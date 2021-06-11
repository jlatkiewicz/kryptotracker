package dreamteam.kryptotracker;

import dreamteam.kryptotracker.domain.user.UserService;
import dreamteam.kryptotracker.domain.user.UserState;
import dreamteam.kryptotracker.domain.wallet.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static dreamteam.kryptotracker.domain.user.UserState.TERMINATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class KryptotrackerApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;

    @Test
	void contextLoads() {
	}

	@Test
    void flowTest() {
	    //user is created
        String username = "newUser";
        userService.registerUser(username, "password").block();

        //user should exist
        assertNotNull(userService.findBy(username).block());

        //wallet should be 0
        assertEquals(walletService.findBy(username).block().getBitcoinAmount(), BigDecimal.ZERO);

        //fund are added
        walletService.addBitcoin(username, BigDecimal.TEN).block();
        assertEquals(walletService.findBy(username).block().getBitcoinAmount(), BigDecimal.TEN);

        //funds are subtracted
        walletService.subtractBitcoin(username, BigDecimal.valueOf(5)).block();
        assertEquals(walletService.findBy(username).block().getBitcoinAmount(), BigDecimal.valueOf(5));

        //user is terminated
        userService.updateState(username, TERMINATED).block();
        assertEquals(userService.findBy(username).block().getUserState(), TERMINATED);
    }
}
