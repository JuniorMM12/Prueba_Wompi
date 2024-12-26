Feature: Validar transaccion con qr erroneos
  Yo como usuario requerio validar el correcto funcionamiento de
  la api de transaccion en escenarios no exitosos para comprobar
  que el servicio se comporta de manera adecuada

  Scenario Outline: Pago por qr bancolombia con errores
    Given el usuario consume el servicio transaccional
    When envie la informacion correspondiente a la transaccion
      | service   | amount   | acceptanceToken   | acceptPersonalAuth   | signature   | customerEmail   | fullname   | phoneNumber   | typePayment   | reference   | redirecturl   | paymentDescription   | userType   | sanboxStatus   |
      | <service> | <amount> | <acceptanceToken> | <acceptPersonalAuth> | <signature> | <customerEmail> | <fullname> | <phoneNumber> | <typePayment> | <reference> | <redirecturl> | <paymentDescription> | <userType> | <sanboxStatus> |
    Then el podra ver que el servicio responde de manera adecuada a peticiones erroneas

    Examples:
      | service      | amount  | acceptanceToken | acceptPersonalAuth | signature | customerEmail | fullname      | phoneNumber   | paymentDescription   | userType | sanboxStatus | typePayment    | reference | redirecturl                                               |
      #Campos obligatorio con valores invalidos
      | transactions | 3000000 | $·%%/%          | [EMPTY]            | [EMPTY]   | [EMPTY]       | Pepito Prubea | +573017263547 | Prueba de pago wompi | PERSON   | APPROVED     | BANCOLOMBIA_QR | Prueba    | https://webhook.site/f4d1c228-d075-4f63-aad0-7599a5997449 |
      | transactions | 3000000 | [EMPTY]         | [EMPTY]            | [$·%%/%   | [EMPTY]       | Pepito Prubea | +573017263547 | Prueba de pago wompi | PERSON   | APPROVED     | BANCOLOMBIA_QR | Prueba    | https://webhook.site/f4d1c228-d075-4f63-aad0-7599a5997449 |
      | transactions | 3000000 | [EMPTY]         | [EMPTY]            | [EMPTY]   | $·%%/%        | Pepito Prubea | +573017263547 | Prueba de pago wompi | PERSON   | APPROVED     | BANCOLOMBIA_QR | Prueba    | https://webhook.site/f4d1c228-d075-4f63-aad0-7599a5997449 |
