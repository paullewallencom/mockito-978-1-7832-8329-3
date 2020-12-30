package com.packtpub.chapter07;

import com.packtpub.chapter07.dto.PaymentAdviceDto;

public interface PayPalFacade {

	void sendAdvice(PaymentAdviceDto paymentAdviceDto);

}
