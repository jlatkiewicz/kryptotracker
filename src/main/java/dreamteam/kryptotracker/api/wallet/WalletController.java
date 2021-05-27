package dreamteam.kryptotracker.api.wallet;

import dreamteam.kryptotracker.domain.wallet.WalletService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController("/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}")
    public Mono<WalletResponse> findBy(@RequestParam("userId") String userId) {
        return walletService.findBy(userId)
                .map(wallet -> WalletResponse.from(wallet, userId))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(userId)));
    }

    @PostMapping("/add/{userId}/{amount}")
    public Mono<WalletResponse> addBitcoin(@RequestParam("userId") String userId, @RequestParam("amount") String amount) {
        return walletService.addBitcoin(userId, new BigDecimal(amount))
                .map(wallet -> WalletResponse.from(wallet, userId));
    }

    @PostMapping("/subtract/{userId}/{amount}")
    public Mono<WalletResponse> subtractBitcoin(@RequestParam("userId") String userId, @RequestParam("amount") String amount) {
        return walletService.subtractBitcoin(userId, new BigDecimal(amount))
                .map(wallet -> WalletResponse.from(wallet, userId));
    }

    @PostMapping("/set/{userId}/{amount}")
    public Mono<WalletResponse> setBitcoin(@RequestParam("userId") String userId, @RequestParam("amount") String amount) {
        return walletService.setBitcoin(userId, new BigDecimal(amount))
                .map(wallet -> WalletResponse.from(wallet, userId));
    }
}
