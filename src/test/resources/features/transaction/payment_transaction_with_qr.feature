Feature: Validar transaccion con qr
  Yo como usuario requerio validar el correcto funcionamiento de la
  api haciendo uso del medio de pago por qr para garantizar que se realice
  de manera adecuada las transacciones por este medio

  Scenario Outline: Pago por qr bancolombia
    Given el usuario consume el servicio transaccional
    When envie la informacion correspondiente a la transaccion
      | service   | amount   | acceptanceToken   | acceptPersonalAuth   | signature   | customerEmail   | fullname   | phoneNumber   | typePayment   | reference   | redirecturl   | paymentDescription   | userType   | sanboxStatus   |
      | <service> | <amount> | <acceptanceToken> | <acceptPersonalAuth> | <signature> | <customerEmail> | <fullname> | <phoneNumber> | <typePayment> | <reference> | <redirecturl> | <paymentDescription> | <userType> | <sanboxStatus> |
    Then el podra ver que el servicio responde de manera adecuada
      | sanboxStatus   |
      | <sanboxStatus> |

    Examples:
      | service      | amount  | acceptanceToken | acceptPersonalAuth | signature | customerEmail | fullname      | phoneNumber   | paymentDescription   | userType | sanboxStatus | typePayment    | reference | redirecturl                                               |
      | transactions | 3000000 | [EMPTY]         | [EMPTY]            | [EMPTY]   | [EMPTY]       | Pepito Prubea | +573017263547 | Prueba de pago wompi | PERSON   | APPROVED     | BANCOLOMBIA_QR | Prueba    | https://webhook.site/f4d1c228-d075-4f63-aad0-7599a5997449 |
      | transactions | 3000000 | [EMPTY]         | [EMPTY]            | [EMPTY]   | [EMPTY]       | Pepito Prubea | +573017263547 | Prueba de pago wompi | PERSON   | DECLINED     | BANCOLOMBIA_QR | Prueba    | https://webhook.site/f4d1c228-d075-4f63-aad0-7599a5997449 |
      | transactions | 3000000 | [EMPTY]         | [EMPTY]            | [EMPTY]   | [EMPTY]       | Pepito Prubea | +573017263547 | Prueba de pago wompi | PERSON   | ERROR        | BANCOLOMBIA_QR | Prueba    | https://webhook.site/f4d1c228-d075-4f63-aad0-7599a5997449 |