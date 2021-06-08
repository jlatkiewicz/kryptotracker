package dreamteam.kryptotracker.api.wallet;

import dreamteam.kryptotracker.domain.wallet.WalletService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @GetMapping("/wallet/{userId}")
    public Mono<WalletResponse> findBy(@PathVariable("userId") String userId) {
        return walletService.findBy(userId)
                .map(wallet -> WalletResponse.from(wallet, userId))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(userId)));
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/wallet/add/{userId}/{amount}")
    public Mono<WalletResponse> addBitcoin(@PathVariable("userId") String userId, @PathVariable("amount") String amount) {
        return walletService.addBitcoin(userId, new BigDecimal(amount))
                .map(wallet -> WalletResponse.from(wallet, userId));
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/wallet/subtract/{userId}/{amount}")
    public Mono<WalletResponse> subtractBitcoin(@PathVariable("userId") String userId, @PathVariable("amount") String amount) {
        return walletService.subtractBitcoin(userId, new BigDecimal(amount))
                .map(wallet -> WalletResponse.from(wallet, userId));
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/wallet/set/{userId}/{amount}")
    public Mono<WalletResponse> setBitcoin(@PathVariable("userId") String userId, @PathVariable("amount") String amount) {
        return walletService.setBitcoin(userId, new BigDecimal(amount))
                .map(wallet -> WalletResponse.from(wallet, userId));
    }
}
