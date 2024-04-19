package francescosileoni.u5w3d5.services;

import francescosileoni.u5w3d5.entities.User;
import francescosileoni.u5w3d5.exceptions.BadRequestException;
import francescosileoni.u5w3d5.payloads.UserRequest;
import francescosileoni.u5w3d5.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO usersDAO;
    @Autowired
    PasswordEncoder encoder;


    public User findByEmail(String email) {
        return this.usersDAO.findByEmail(email).orElseThrow(()->new NotFoundEx("L'utente con email "+email +" non esiste"));
    }


    public User findById(long id){
        return this.usersDAO.findById(id).orElseThrow(()->new NotFoundEx(id+ "non c'è"));
    }


    public User save(UserRequest payload){
        this.usersDAO.findByEmail(payload.email()).ifPresent (user->{throw new BadRequestException("L'email "+user.getEmail()+"è già in uso");});
        User newU=new User(payload.name(), payload.surname(), payload.dateOfBirth(),"https://ui-avatars.com/api/?name="+ payload.name() + "+" + payload.surname(), payload.email(), encoder.encode(payload.password()));
        return usersDAO.save(newU);
    }

    public void delete(long id){
        User found =this.findById(id);
        this.usersDAO.delete(found);
    }

    public User changeRole(long id, User upgrated){
        User found=this.findById(id);
        found.setRole(upgrated.getRole());
        return this.usersDAO.save(found);
    }


    public Page<User> gettingUs(int size, int page, String sort_by){
        if(size>150)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(sort_by));
        return this.usersDAO.findAll(pageable);
    }


}
