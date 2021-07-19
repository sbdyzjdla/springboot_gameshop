package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.service.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@RestController
public class IamportController {

    private final OrderService orderService;
    private IamportClient api;

    public IamportController(final OrderService orderService) {
        this.api = new IamportClient("2607008187124067",
                "5LS32LUgoV1qZRAOBH20IGIXjnP9ygmE1fH40BlpwmPwdplP6nQP4zNnXCxvTU9VaRZbtkA0HpxUPSKV");
        this.orderService = orderService;
    }

    @PostMapping("/veirfyIamport/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid (Model model, Locale locale , HttpSession session
			, @PathVariable(value= "imp_uid") String imp_uid, @LoginUser SessionUser user) throws IamportResponseException, IOException
        {
            int total_price = orderService.order_ready_total_price(user.getId());
            //if(total_price == api.paymentByImpUid(imp_uid).getResponse().getAmount())
            System.out.println("가격비교 !!" + total_price + " api" + api.paymentByImpUid(imp_uid).getResponse().getAmount());
            return api.paymentByImpUid(imp_uid);
        }
}

