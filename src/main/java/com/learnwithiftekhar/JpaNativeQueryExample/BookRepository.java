package com.learnwithiftekhar.JpaNativeQueryExample;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(
            value = "select * from books b where lower(b.title) like %:title%",
            nativeQuery = true
    )
    List<Book> findBooksByTitleThatContainsWord(@Param("title") String title);

    @Query(
            value = "select * from books where match(title, author, genre) against (:keyword)",
            nativeQuery = true
    )
    Page<Book> search(@Param("keyword") String keyword, Pageable pageable);


    @Query(
            value = "update books set price = :price where id = :id",
            nativeQuery = true
    )
    @Transactional
    @Modifying
    void updatePrice(double price, Long id);
}
