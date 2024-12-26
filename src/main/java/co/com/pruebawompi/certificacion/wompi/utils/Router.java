package co.com.pruebawompi.certificacion.wompi.utils;

public enum Router {

    TRANSACTION_JSON("src/test/resources/data/transaction_body.json");

    private final String routerFile;

    Router(String routerFile){
        this.routerFile = routerFile;
    }

    public String getRouterFile(){
        return routerFile;
    }
}
