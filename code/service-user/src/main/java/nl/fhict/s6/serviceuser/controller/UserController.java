package nl.fhict.s6.serviceuser.controller;

import nl.fhict.s6.libraryrest.controller.BaseController;
import nl.fhict.s6.libraryrest.exception.NoObjectById;
import nl.fhict.s6.serviceuser.converters.UserDaoConverter;
import nl.fhict.s6.serviceuser.datamodels.UserDao;
import nl.fhict.s6.serviceuser.dto.UserDto;
import nl.fhict.s6.serviceuser.event.UsernameChangedEvent;
import nl.fhict.s6.serviceuser.messaging.EncapsulatingMessageGenerator;
import nl.fhict.s6.serviceuser.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController extends BaseController<UserDao, UserDto> {
    private final UserService userService;
    private final UserDaoConverter userDaoConverter;
    private final AmqpTemplate rabbitTemplate;
    private EncapsulatingMessageGenerator encapsulatingMessageGenerator;
    @Value("${gamehighlights.rabbitmq.exchange}")
    private String exchange;
    @Value("${gamehighlights.rabbitmq.routingkey}")
    private String routingkey;

    public UserController(UserService userService, UserDaoConverter userDaoConverter, AmqpTemplate rabbitTemplate, EncapsulatingMessageGenerator encapsulatingMessageGenerator) {
        super(userService, userDaoConverter);
        this.userService = userService;
        this.userDaoConverter = userDaoConverter;
        this.rabbitTemplate = rabbitTemplate;
        this.encapsulatingMessageGenerator = encapsulatingMessageGenerator;
    }

    @Override
    @PostMapping({""})
    public ResponseEntity post(@ModelAttribute UserDto userDto) {
        return ResponseEntity.notFound().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> put(@PathVariable("id") Long userId,@ModelAttribute("userDto") UserDto userDto)
    {
        if(userId != userDto.getUserId())
        {
            return ResponseEntity.badRequest().build();
        }
        UserDao userDao = userDaoConverter.objectToObjectDao(userDto);
        try {
            UserDao updated = userService.update(userDao);
            if(userDao.getId().equals(updated.getId()) && userDao.getUsername().equals(updated.getUsername()))
            {
                rabbitTemplate.convertAndSend(exchange,routingkey,encapsulatingMessageGenerator.generateMessage(new UsernameChangedEvent(updated.getId(),updated.getUsername())));
            }
            return ResponseEntity.ok().build();
        } catch (NoObjectById noObjectById) {
            return ResponseEntity.badRequest().build();
        }
    }
}
