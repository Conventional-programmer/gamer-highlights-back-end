package nl.fhict.s6.serviceuser.controller;

import nl.fhict.s6.libraryrest.controller.BaseController;
import nl.fhict.s6.serviceuser.converters.UserDaoConverter;
import nl.fhict.s6.serviceuser.datamodels.UserDao;
import nl.fhict.s6.serviceuser.dto.UserDto;
import nl.fhict.s6.serviceuser.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<UserDao, UserDto> {
    private UserService userService;
    private UserDaoConverter userDaoConverter;
    public UserController(UserService userService, UserDaoConverter userDaoConverter) {
        super(userService, userDaoConverter);
        this.userService = userService;
        this.userDaoConverter = userDaoConverter;
    }
}
