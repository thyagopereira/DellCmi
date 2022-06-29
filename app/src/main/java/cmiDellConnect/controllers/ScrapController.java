package cmiDellConnect.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cmiDellConnect.services.ScrapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(value= "Healthcheck")
public class ScrapController {

    private String HOST = "http://owc-lgd.dsc.ufcg.edu.br";
    private String PORT = "8081" ;

    @Autowired
    private ScrapService service  = new ScrapService();

    @ApiOperation(value = "HealthCheck")
    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return new ResponseEntity<String>("Service is running on door :8080. With safe Connection.", HttpStatus.OK);
    } 

    @ApiOperation(value = "Init a connection with the specified URL")
    @GetMapping("/init")
    public ResponseEntity<String> getResponse(){
        try {
            return new ResponseEntity<String>(service.initConnection(HOST, PORT), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>("Proxy is not right setted" , HttpStatus.BAD_GATEWAY);

    }
}
