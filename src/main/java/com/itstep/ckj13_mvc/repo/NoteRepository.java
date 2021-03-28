package com.itstep.ckj13_mvc.repo;

import com.itstep.ckj13_mvc.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer > {
//List<Note> findByTitleContainingOrMessageContaining(String word, String word1);
    @Query("SELECT n FROM Note n where n.title LIKE :word or n.message like :word or n.dateOfCreation like :word")
    List<Note> search(@Param("word") String word);
}
