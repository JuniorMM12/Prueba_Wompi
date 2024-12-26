package co.com.pruebawompi.certificacion.wompi.interactions;

import co.com.pruebawompi.certificacion.wompi.utils.Constant;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class GetStatusTrax implements Interaction {


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(Constant.URL_TOKEN));
        String idTrx = actor.recall("idTrx");
        actor.attemptsTo(
              Get.resource("transactions/" + idTrx).with(
                      requestSpecification -> requestSpecification
                              .relaxedHTTPSValidation()
                              .contentType(ContentType.JSON)
                              .header("Authorization", "Bearer " + Constant.PUBLIC_KEY)
              )
        );

    }

    public static GetStatusTrax getStatusTrax(){
        return new GetStatusTrax();
    }
}
