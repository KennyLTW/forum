/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ouhk.comps380f.model.Comment;

/**
 *
 * @author LTW
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
    
    @Query(value = "select * from comment where postid = ?1", nativeQuery = true)
    List<Comment> findByPostid(long postid);
    
}
