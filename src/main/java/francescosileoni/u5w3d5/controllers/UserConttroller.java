package francescosileoni.u5w3d5.controllers;

import francescosileoni.u5w3d5.entities.User;
import francescosileoni.u5w3d5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserConttroller {

    @Autowired
    UserService userService;
    //http://localhost:3002/users
    @GetMapping()
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "15")int size,
            @RequestParam(defaultValue = "nome")String sort_by

    ){
        return this.userService.gettingUs(page, size, sort_by);
    }


}
