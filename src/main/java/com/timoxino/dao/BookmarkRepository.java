package com.timoxino.dao;

import com.timoxino.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Class represents DAO to work with {@link Bookmark} entity.
 *
 * @author timoxino.
 */
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>
{
    Collection<Bookmark> findByAccountUsername(String username);
}
