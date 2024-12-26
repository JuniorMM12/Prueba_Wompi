package co.com.pruebawompi.certificacion.wompi.runners.transactionsuccessful;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/transaction/payment_transaction_with_qr.feature",
        glue = "co.com.pruebawompi.certificacion.wompi.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class PaymentTransactionWithQr {}