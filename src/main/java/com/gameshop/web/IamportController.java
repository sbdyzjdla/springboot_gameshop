//package com.gameshop.web;
//
//import com.siot.IamportRestClient.IamportClient;
//import com.siot.IamportRestClient.exception.IamportResponseException;
//import com.siot.IamportRestClient.response.IamportResponse;
//import com.siot.IamportRestClient.response.Payment;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Locale;
//
//
//@Controller
//public class IamportController {
//
//    private IamportClient api;
//
//    public IamportController() {
//        this.api = new IamportClient("2607008187124067",
//                "5LS32LUgoV1qZRAOBH20IGIXjnP9ygmE1fH40BlpwmPwdplP6nQP4zNnXCxvTU9VaRZbtkA0HpxUPSKV");
//    }
//
//    @ResponseBody
//    @PostMapping("/veirfyIamport/{imp_uid}")
//    public IamportResponse<Payment> paymentByImpUid (Model model, Locale locale , HttpSession session
//			, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException
//        {
//            return api.paymentByImpUid(imp_uid);
//        }
//}
//
