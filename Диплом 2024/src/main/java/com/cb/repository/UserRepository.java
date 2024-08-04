package com.cb.repository;

import com.cb.dto.UserDto;
import com.cb.model.Category;
import com.cb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);


    Optional<User> findUserByEmail(String email);
//
//    User findById(Integer id_master);
    //@Query("SELECT u.schedule FROM User u WHERE u.id = 3 ")
    User findAllTimeById(Integer id_master);
    @Query(nativeQuery = true, value =
            "SELECT schedule_data.time " +
                    "FROM users, " +
                    "JSON_TABLE(users.schedule, '$[*]' COLUMNS (time VARCHAR(10) PATH '$.time')) AS schedule_data"
    )
    List<String> getAllTimes();
    @Query("SELECT u FROM User u WHERE u.id_roles = '3' ")
    List<User> getAllMaster();

    @Query("SELECT u FROM User u  where u.id_roles != '1' and u.delete=false order by u.id")
    List<User> getAllUser();


}
