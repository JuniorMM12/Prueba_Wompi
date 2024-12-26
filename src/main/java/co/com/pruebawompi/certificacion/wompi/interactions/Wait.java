package co.com.pruebawompi.certificacion.wompi.interactions;

import net.serenitybdd.model.time.InternalSystemClock;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class Wait implements Interaction {

    private final int time;

    public Wait(int time) {
        this.time = time;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        new InternalSystemClock().pauseFor(time);
    }

    public static Wait per(int time){
        return Tasks.instrumented(Wait.class, time);
    }
}
