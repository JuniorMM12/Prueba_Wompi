package co.com.pruebawompi.certificacion.wompi.questions;

import co.com.pruebawompi.certificacion.wompi.models.Model;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Map;

public class ValidateErrors implements Question<Boolean> {


    @Override
    public Boolean answeredBy(Actor actor) {
        String responseGet = SerenityRest.lastResponse().jsonPath().getString("error.messages");
        return responseGet.contains("Email inválido") ||
                responseGet.contains("La firma es inválida") ||
                responseGet.contains("El token de aceptación está en un formato incorrecto");
    }

    public static ValidateErrors validateErrors() {
        return new ValidateErrors();
    }
}
