package co.com.pruebawompi.certificacion.wompi.utils;

import co.com.pruebawompi.certificacion.wompi.models.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class BodyRequest {

    private BodyRequest(){}

    //bodyQrBancolombiaPayment se encarga setear los valores del request del servicio
    public static String bodyQrBancolombiaPayment(Model model, Map<String, String> response, Map<String, String> token, Map<String, String> tokenAuth){

        String jsoData = "";
        String pathJson = Router.valueOf("TRANSACTION_JSON").getRouterFile();
        String date = Generator.date() + ".000Z";
        String reference = model.getReference() + Generator.idRandom();
        String signature = reference + model.getAmount() + "COP" + date + Constant.INTEGRITY_KEY;
        System.out.println(signature);
        try {
            jsoData = new String(Files.readAllBytes(Paths.get(pathJson)));
            jsoData = jsoData
            .replace("$acceptanceToken", model.getAcceptanceToken().isEmpty()?
                    token.get("acceptance_token"): model.getAcceptanceToken())
            .replace("$acceptPersonalAuth", model.getAcceptPersonalAuth().isEmpty() ?
                    tokenAuth.get("acceptance_token"):model.getAcceptPersonalAuth())
            .replace("\"$amount\"", model.getAmount())
            .replace("$signature", model.getSignature().isEmpty()?
                    Generator.generateSHA256(signature):model.getSignature())
            .replace("$customerEmail", model.getCustomerEmail().isEmpty()?
                    response.get("email"):model.getCustomerEmail())
            .replace("$typePayment",model.getTypePayment())
            .replace("$paymentDescription",model.getPaymentDescription())
            .replace("$usertype", model.getUserType())
            .replace("$sandboxStatus", model.getSanboxStatus())
            .replace("$reference", reference)
            .replace("$redirecturl", model.getRedirecturl())
            .replace("$expirationTime", date)
            .replace("$phoneNumber",model.getPhoneNumber())
            .replace("$fullName",model.getFullname());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsoData;
    }
}
