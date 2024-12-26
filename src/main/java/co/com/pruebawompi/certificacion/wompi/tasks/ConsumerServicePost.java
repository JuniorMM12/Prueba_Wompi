package co.com.pruebawompi.certificacion.wompi.tasks;

import co.com.pruebawompi.certificacion.wompi.interactions.GenerateToken;
import co.com.pruebawompi.certificacion.wompi.interactions.Post;
import co.com.pruebawompi.certificacion.wompi.models.Model;
import co.com.pruebawompi.certificacion.wompi.utils.BodyRequest;
import co.com.pruebawompi.certificacion.wompi.utils.Constant;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import io.restassured.http.ContentType;

import javax.swing.*;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsumerServicePost implements Task {

    private Model model;
    public ConsumerServicePost(Model model) {
        this.model = model;
    }

    @Override
    public <T extends Actor> void performAs(T actor){
        actor.attemptsTo(GenerateToken.generateToken());
        Map<String , String> response = SerenityRest.lastResponse().jsonPath().get("data");
        Map<String, String> token = SerenityRest.lastResponse().jsonPath().getMap("data.presigned_acceptance");
        Map<String, String> tokenAuth = SerenityRest.lastResponse().jsonPath().getMap("data.presigned_personal_data_auth");

        actor.attemptsTo(
            Post.to(model.getService()).with(
                        requestSpecification -> requestSpecification
                        .relaxedHTTPSValidation()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + Constant.PUBLIC_KEY)
                                .body(BodyRequest.bodyQrBancolombiaPayment(model, response, token, tokenAuth ))
            )
        );

        actor.remember("idTrx", SerenityRest.lastResponse().jsonPath().getString("data.id")); //Se almacena el id de transccion retornado por el servicio de transaccion

    }

    public static ConsumerServicePost consumerServicePost(Model model) {
        return instrumented(ConsumerServicePost.class, model);
    }
}