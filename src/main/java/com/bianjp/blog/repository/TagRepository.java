package com.bianjp.blog.repository;

import com.bianjp.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
  Tag findByName(String name);

  List<Tag> findAllByPostCountGreaterThan(int postCount);

  @Query(
    nativeQuery = true,
    value =
        "UPDATE tag SET post_count = (SELECT COUNT(*) FROM post_tag WHERE tag_id = ?1) WHERE id = ?1"
  )
  @Modifying
  void updatePostCount(int id);

  @Query(
    nativeQuery = true,
    value = "UPDATE tag SET post_count = (SELECT COUNT(*) from post_tag WHERE tag_id = tag.id)"
  )
  @Modifying
  void updatePostCount();
}
