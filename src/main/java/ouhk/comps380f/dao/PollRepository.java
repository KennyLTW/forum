/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import static java.lang.ProcessBuilder.Redirect.from;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ouhk.comps380f.controller.AdminController.Poll;
import ouhk.comps380f.model.NewPoll;

/**
 *
 * @author LTW
 */
public interface PollRepository extends JpaRepository<NewPoll, String> {

    //find the new poll
    // @Query(value = "SELECT * FROM POLL p WHERE p.id = ?1")
    //List<NewPoll> findTheNewOne(String name);
    //Find the new one (create_time) poll
    List<NewPoll> findTopByOrderByIdDesc();

    @Query(value = "select * from Poll where question = ?1", nativeQuery = true)
    List<NewPoll> findByQuestion(String question);
   

}
