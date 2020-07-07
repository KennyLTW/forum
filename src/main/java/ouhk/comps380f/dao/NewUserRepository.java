/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ouhk.comps380f.model.NewUser;

/**
 *
 * @author LTW
 */
public interface NewUserRepository extends JpaRepository<NewUser, String> {
/*
    @Transactional
    @Modifying
    @Query("UPDATE users SET password = password WHERE username = username")
    public void updateByUsername(@Param(value = "password") String username);

    @Transactional
    @Modifying
    @Query("SELECT * FROM users WHERE username = username")
    public String findByUsername(@Param(value = "username") String username);
*/

}
