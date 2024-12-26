package co.com.pruebawompi.certificacion.wompi.stepdefinitions;

import co.com.pruebawompi.certificacion.wompi.models.Model;
import co.com.pruebawompi.certificacion.wompi.questions.ValidateStatusQrTrx;
import co.com.pruebawompi.certificacion.wompi.tasks.ConsumerServicePost;
import co.com.pruebawompi.certificacion.wompi.utils.Constant;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class PaymentTransactionStepDefinition {

    //Configuración previa del actor quien realizará las acciones
    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }

    // configModel se encarga de configurar las variables provenientes del feature
    @DataTableType(replaceWithEmptyString = "[EMPTY]")
    public static Model configModel(Map<String, String> row){
        return Model.configModel(row);
    }

    // Metodo encargado de agregar la URL Inicial del consumo
    @Given("el usuario consume el servicio transaccional")
    public void elUsuarioConsumeElServicioTransaccional() {
        OnStage.theActorInTheSpotlight().whoCan(CallAnApi.at(Constant.URL_BASE));
    }

    //Metodo donde se llama la task encargada de consumir el servicio post de transccion
    @When("envie la informacion correspondiente a la transaccion")
    public void envieLaInformacionCorrespondienteALaTransaccion(List<Model> modelList) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConsumerServicePost.consumerServicePost(modelList.get(0))
        );
    }

    //Metodo encargado de realizar las validaciones finales
    @Then("el podra ver que el servicio responde de manera adecuada")
    public void elPodraVerQueElServicioRespondeDeManeraAdecuada(List<Model> modelList) {
       OnStage.theActorInTheSpotlight().should(
               seeThatResponse(response->response.statusCode(201)),
               seeThat(ValidateStatusQrTrx.validateStatusQrTrx(modelList.get(0)), Matchers.is(true))
       );
    }

}
