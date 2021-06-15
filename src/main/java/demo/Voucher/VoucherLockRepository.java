package demo.Voucher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface VoucherLockRepository extends JpaRepository<VoucherLock, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select 1 from VoucherLock where id = 1")
    void lockVoucherRequest();
}
