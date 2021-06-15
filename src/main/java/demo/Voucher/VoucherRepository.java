package demo.Voucher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    @Query("select count(*) from Voucher v where v.userId = :userId")
    int countVouchersOfUser(@Param("userId") Long userId);
}
