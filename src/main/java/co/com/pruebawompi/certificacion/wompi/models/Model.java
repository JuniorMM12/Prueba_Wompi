package co.com.pruebawompi.certificacion.wompi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.htmlunit.corejs.javascript.Sorting;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Model {

    private String service;
    private String amount;
    private String typePayment;
    private String reference;
    private String redirecturl;
    private String paymentDescription;
    private String userType;
    private String sanboxStatus;
    private String fullname;
    private String phoneNumber;
    private String acceptanceToken;
    private String acceptPersonalAuth;
    private String signature;
    private String customerEmail;

    public static Model configModel(Map<String, String> row){
        return new Model(
                row.get("service"),
                row.get("amount"),
                row.get("typePayment"),
                row.get("reference"),
                row.get("redirecturl"),
                row.get("paymentDescription"),
                row.get("userType"),
                row.get("sanboxStatus"),
                row.get("fullname"),
                row.get("phoneNumber"),
                row.get("acceptanceToken"),
                row.get("acceptPersonalAuth"),
                row.get("signature"),
                row.get("customerEmail")
        );
    }
}