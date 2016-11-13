package com.buechler.spring.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buechler.spring.data.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  /**
   * Find all persons with the given lastname. Since there is no named query declared, this method
   * will be translated into a query.
   * 
   * @param lastname
   * @return
   */
  List<Person> findByLastNameContainingIgnoreCaseOrderByLastNameAsc(String lastname);

  /**
   * Find all persons with the given firstname. This method will be translated into a query using
   * the one declared in the {@link Query} annotation.
   * 
   * @param firstName
   * @return
   */
  @Query("select p from Person p where p.firstName = :firstName order by p.firstName ASC")
  List<Person> findByFirstNameOrderByFirstNameAsc(@Param("firstName") String firstName);
  
  List<Person> findAllByOrderByFirstNameAsc();
  
  List<Person> findFirst2ByOrderByLastNameAsc();
  
}
