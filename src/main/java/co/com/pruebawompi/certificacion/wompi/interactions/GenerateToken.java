package co.com.pruebawompi.certificacion.wompi.interactions;

import co.com.pruebawompi.certificacion.wompi.utils.Constant;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class GenerateToken implements Interaction {


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(Constant.URL_TOKEN));
        actor.attemptsTo(
              Get.resource("merchants/" + Constant.PUBLIC_KEY).with(
                      requestSpecification -> requestSpecification
                              .relaxedHTTPSValidation()
                              .contentType(ContentType.JSON)
              )
        );

    }

    public static GenerateToken generateToken(){
        return new GenerateToken();
    }
}
