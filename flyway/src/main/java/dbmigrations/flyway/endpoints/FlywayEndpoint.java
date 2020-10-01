package dbmigrations.flyway.endpoints;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbmigrations.flyway.services.FlywayService;

@RestController
@RequestMapping("flyway")
public class FlywayEndpoint {

    @Autowired
    FlywayService service;

    @GetMapping("/history")
    Object getHistory(){
        return service.getHistory(PageRequest.of(0,100));
    }
    
}
