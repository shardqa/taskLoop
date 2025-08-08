package com.todoapp.repository;

import com.todoapp.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{'userId': ?0, 'state.completed': false, 'state.deleted': false}")
    List<Task> findByUserIdOrderByMetadataPosition(String userId);

    @Query("{'userId': ?0, 'state.deleted': false}")
    List<Task> findByUserIdAndNotDeleted(String userId);

    @Query("{'userId': ?0, 'state.completed': false, 'state.deleted': false}")
    Page<Task> findByUserIdOrderByMetadataPosition(String userId, Pageable pageable);

    @Query("{'userId': ?0, 'state.completed': true, 'state.deleted': false}")
    List<Task> findCompletedByUserId(String userId);

    @Query("{'userId': ?0, 'state.completed': true, 'state.deleted': false}")
    Page<Task> findCompletedByUserId(String userId, Pageable pageable);

    @Query("{'userId': ?0, 'isRecurrent': true, 'state.completed': false, 'state.deleted': false}")
    List<Task> findRecurrentTasksByUserId(String userId);

    @Query("{'userId': ?0, 'isRecurrent': true, 'state.completed': false, 'state.deleted': false}")
    Page<Task> findRecurrentTasksByUserId(String userId, Pageable pageable);

    @Query("{'userId': ?0, 'metadata.category': ?1, 'state.completed': false, 'state.deleted': false}")
    List<Task> findByUserIdAndCategoryOrderByMetadataPosition(String userId, String category);

    @Query("{'userId': ?0, 'metadata.category': ?1, 'state.completed': false, 'state.deleted': false}")
    Page<Task> findByUserIdAndCategoryOrderByMetadataPosition(String userId, String category, Pageable pageable);

    Optional<Task> findByIdAndUserId(String id, String userId);

    @Query("{'userId': ?0, 'state.deleted': false}")
    List<Task> findAllByUserId(String userId);

    @Query("{'userId': ?0, 'state.deleted': false}")
    Page<Task> findAllByUserId(String userId, Pageable pageable);

    @Query(value = "{'userId': ?0, 'state.completed': false, 'state.deleted': false}", count = true)
    long countActiveTasksByUserId(String userId);

    @Query("{'userId': ?0, 'state.deleted': true}")
    List<Task> findDeletedByUserId(String userId);

    @Query("{'userId': ?0, 'state.deleted': true}")
    Page<Task> findDeletedByUserId(String userId, Pageable pageable);
} 