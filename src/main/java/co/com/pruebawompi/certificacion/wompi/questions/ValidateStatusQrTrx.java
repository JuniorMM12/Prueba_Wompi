package co.com.pruebawompi.certificacion.wompi.questions;

import co.com.pruebawompi.certificacion.wompi.interactions.GetStatusTrax;
import co.com.pruebawompi.certificacion.wompi.interactions.Wait;
import co.com.pruebawompi.certificacion.wompi.models.Model;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Map;

public class ValidateStatusQrTrx implements Question<Boolean> {

    private Model model;

    private ValidateStatusQrTrx(Model model){
        this.model = model;
    }

    /*La validacion para este caso es confirmar que se retorne el id de qr, que el id de transccion
    sean igual, que retorne la imagen de qr y el id del qr*/
    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(
                Wait.per(20000),
                GetStatusTrax.getStatusTrax()
        );
        Map<String, String> responseGet = SerenityRest.lastResponse().jsonPath().getMap("data");
        Map<String, String> responseInfoQr = SerenityRest.lastResponse().jsonPath().getMap("data.payment_method.extra");
        return responseGet.get("id").equalsIgnoreCase(actor.recall("idTrx")) &&
                model.getSanboxStatus().equalsIgnoreCase(responseGet.get("status")) &&
                !responseInfoQr.get("qr_image").isEmpty() &&
                !responseInfoQr.get("qr_id").isEmpty() ;
    }

    public static ValidateStatusQrTrx validateStatusQrTrx(Model model){
        return new ValidateStatusQrTrx(model);
    }
}