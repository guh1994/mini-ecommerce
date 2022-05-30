package br.com.test.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "testController" )
public class TestController
{

    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseEntity<String> testController()
    {
        return ResponseEntity.ok( testService.testReturn() );
    }

}
