package demo.Voucher;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voucher")
@Transactional
public class VoucherManager {

    private final VoucherRepository     voucherRepository;
    private final VoucherLockRepository voucherLockRepository;

    public VoucherManager(
            VoucherRepository voucherRepository,
            VoucherLockRepository voucherLockRepository) {
        this.voucherRepository = voucherRepository;
        this.voucherLockRepository = voucherLockRepository;
    }

    @GetMapping("/request")
    public Voucher request(@RequestParam(name="userid") Long userId) throws InterruptedException {
        voucherLockRepository.lockVoucherRequest();
        int voucherCount = voucherRepository.countVouchersOfUser(userId);
        if (voucherCount >= 2) {
            return null;
        }
        Voucher v = Voucher.builder().userId(userId).build();
        voucherRepository.save(v);
        return v;
    }
}