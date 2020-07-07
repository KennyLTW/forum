/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.dao.NewUserRepository;
import ouhk.comps380f.model.NewUser;

/**
 *
 * @author LTW
 */
@Controller
public class NewUserController {

    @Resource
    NewUserRepository newUserRepository;

    public static class Form {

        private String username;
        private String password;
        private String role;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    @GetMapping("/create_acc")
    public ModelAndView create() {
        return new ModelAndView("create_acc", "newuser", new Form());
    }
    
    @PostMapping("/create_acc")
    public View create(Form form) throws IOException {
        NewUser user = new NewUser(form.getUsername(),
                form.getPassword(), form.getRole()
        );
        newUserRepository.save(user);
        return new RedirectView("forum/index", true);
    }

}
