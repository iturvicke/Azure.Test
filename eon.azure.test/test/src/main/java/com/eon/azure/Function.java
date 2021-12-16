package com.eon.azure;

import com.eon.azure.bo.boPrueba;
import com.eon.azure.bo.impl.boPruebaImpl;
import com.eon.azure.common.Cuadrado;
import com.google.gson.Gson;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {

    private boPrueba prueba;
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("funcionServerless")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

     
        // Parse query parameter
        final String query =  request.getQueryParameters().get("lado");
        //final String name = request.getBody().orElse(query);

        /*if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            return request.creasteResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        }*/

        if (query == null) {
            return request.createResponseBuilder(HttpStatus.OK).body("Please pass a name on the query string or in the request body").build();
        } 
        
        prueba = new boPruebaImpl();
        Cuadrado cuadrado =  prueba.calculoArea( Float.parseFloat(query));

        Gson gson = new Gson();
        

        return request.createResponseBuilder(HttpStatus.OK).body(gson.toJson(cuadrado)).build();

    }
}
