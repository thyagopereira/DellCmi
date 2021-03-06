package cmiDellConnect.services;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.springframework.stereotype.Service;

@Service
public class ScrapService {

    public String initConnection(String host, String port) throws IOException, InterruptedException {
        HttpClient client = getClient(host, Integer.parseInt(port));

        String uri = "http://owc-lqd.dsc.ufcg.edu.br:8081/cs/login/login.htm";
        HttpResponse<String> response = client.send(createGetRequest(uri), BodyHandlers.ofString());

        return response.body();
    }


    private HttpClient getClient(String host, int port){
        HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .followRedirects(Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(60))
            //.proxy(ProxySelector.of(new InetSocketAddress(host, port)))
            //.authenticator(Authenticator.getDefault())
            .build();
        
        
        return client ;            
    }


    private HttpRequest createGetRequest(String uri){
        return HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .timeout(Duration.ofMinutes(2))
        .header("Content-Type", "application/json")
        .GET()
        .build();
    }
    
}
