package lab4.demo.dao;

import lab4.demo.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("SELECT u FROM Point u WHERE u.username = ?1")
    Collection<Point> findAllById(String username);
}