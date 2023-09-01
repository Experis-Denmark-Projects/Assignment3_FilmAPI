package experis.filmapi.exceptions.controllers;

import experis.filmapi.mappers.IFranchiseMapper;
import experis.filmapi.services.interfaces.IFranchiseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Go to this url: http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private final IFranchiseService franchiseService;
    private final IFranchiseMapper franchiseMapper;

    public FranchiseController(IFranchiseService franchiseService, IFranchiseMapper franchiseMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
    }
}
