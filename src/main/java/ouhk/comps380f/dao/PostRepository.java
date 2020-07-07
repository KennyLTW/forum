/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ouhk.comps380f.model.Post;

/**
 *
 * @author LTW
 */
public interface PostRepository  extends JpaRepository<Post, Long>  {
    
    @Query(value = "select * from post where posttype =?1",  nativeQuery = true)
    List<Post> findByPosttype(String posttype);
    
}
