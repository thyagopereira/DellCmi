package cmiDellConnect.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(value= "Healthcheck")
public class ScrapController {

    private String URL = "";

    @ApiOperation(value = "HealthCheck")
    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return new ResponseEntity<String>("Service is running on door :8080. With safe Connection.", HttpStatus.OK);
    }
    

}
