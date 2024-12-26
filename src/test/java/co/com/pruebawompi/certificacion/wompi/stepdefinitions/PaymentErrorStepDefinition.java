package co.com.pruebawompi.certificacion.wompi.stepdefinitions;

import co.com.pruebawompi.certificacion.wompi.questions.ValidateErrors;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class PaymentErrorStepDefinition {

    @Then("el podra ver que el servicio responde de manera adecuada a peticiones erroneas")
    public void elPodraVerQueElServicioRespondeDeManeraAdecuadaAPeticionesErroneas() {
        OnStage.theActorInTheSpotlight().should(
            seeThatResponse(response->response.statusCode(422))
                    , seeThat(ValidateErrors.validateErrors(), Matchers.is(true))
        );
    }
}
