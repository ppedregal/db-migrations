package dbmigrations.liquibase.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbmigrations.liquibase.services.LiquibaseService;

@RestController
@RequestMapping("/liquibase")
public class LiquibaseEndpoint {

    @Autowired
    LiquibaseService service;

    @GetMapping("/changelogs")
    Object getChangelog(){
        return service.findChangelogs(PageRequest.of(0, 100));
    }
    
    @GetMapping("/locks")
    Object getLocks(){
        return service.findLocks(PageRequest.of(0, 100));
    }

}
