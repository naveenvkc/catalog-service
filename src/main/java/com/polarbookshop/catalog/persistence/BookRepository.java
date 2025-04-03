package com.polarbookshop.catalog.persistence;

import com.polarbookshop.catalog.entity.BookEntity;
import com.polarbookshop.catalog.entity.PolarBookshopPageProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<BookEntity,Long> {

    Optional<BookEntity> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    @Modifying
    @Transactional
    @Query(value = "delete from book where isbn = :isbn", nativeQuery = true)
    void deleteByIsbn(String isbn);

    @Query(
            value = "select id as id, isbn as isbn, title as title, price as price from book",
            nativeQuery = true,
            countQuery = "SELECT COUNT(id) FROM book"
    )
    Page<PolarBookshopPageProjection> findCatalogAppPageList(Pageable pageable);
}
