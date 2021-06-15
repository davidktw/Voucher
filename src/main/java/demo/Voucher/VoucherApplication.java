package demo.Voucher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class VoucherApplication {

	public VoucherApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(VoucherApplication.class, args);
	}

}
