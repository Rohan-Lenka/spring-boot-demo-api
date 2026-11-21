package com.rohan.demo_api.repository;

import com.rohan.demo_api.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // this annotation not really required, use this just for naming convention.
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    // JpaRepository<Entity Name, Data type of the id of the Entity>

    // 1) Derived Query Methods in Spring Data JPA Repositories [IMP]
    // Lets say we have to find all employees whose name = 'Rahul' and sort them in asc order by their salary.
    // Instead of writing the raw SQL query or the JPQL, we can simply leverage the derived query methods.
    public List<Employee> findByNameOrderBySalaryAsc(String name);
    // u can modify the return type to optional class as well.
    // for more details :- https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    // 2) Custom JPQL / SQL query
    // For complicated SQL queries, it is recommended to write own queries. (JPQL is very similar to native SQL)

    // JPQL simple example :-
    @Query("SELECT e FROM Employee e WHERE e.name = ?1 AND e.email = ?2")
//    @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.email = :email")
    // 2nd method uses the value passed via the param in where clause.
    public List<Employee> findByNameAndEmail(@Param("name") String name, @Param("email") String email);
    // When @Query is applied, the default behavior of Derived Query Methods is dropped.
    // We use the name as Employee coz JPA knows the table name as Employee and not employee_entity.
    // In db, the table gets created with snake_case naming convention hence employee_entity is the name of the table in the db.

    // JPQL complex example :-
    @Query("SELECT e.name, count(e) FROM Employee e GROUP BY e.salary")
    public List<Object[]> countBySalary();
    // Object[] coz the query returns name(String) and count(Number).

    @Transactional
    // This is a very imp annotation which should be used with caution. Proper docs and manual for this ->
    // https://dev.to/gianfcop98/transactions-in-spring-boot-what-transactional-really-does-and-why-it-matters-56a6
    // https://medium.com/@anil.goyal0057/transaction-propagation-in-spring-boot-required-vs-requires-new-with-real-use-cases-9eae38a14e96
    // https://www.reddit.com/r/SpringBoot/comments/1slsqbk/why_shouldnt_we_use_transactional_every_time/
    @Modifying
    // Both of these annotations are required when update type queries are executed.
    @Query("UPDATE Employee e SET e.name = :newName WHERE e.id = :id")
    public int updateNameById(@Param("newName") String name, @Param("id") String id);
    // int coz this update query returns the no. of rows affected.

    // Native SQL example
    @Query(value = "SELECT * FROM employee_entity", nativeQuery = true)
    public List<Employee> findAllEmployees();
}
