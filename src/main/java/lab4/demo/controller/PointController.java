package lab4.demo.controller;

import lab4.demo.dao.PointRepository;
import lab4.demo.entity.Point;
import lab4.demo.service.AreaCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class PointController {
    private final PointRepository pointRepository;
    private final AreaCheck areaCheck;
    private static final Logger logger = LoggerFactory.getLogger(PointController.class);

    PointController(PointRepository pointRepository, AreaCheck areaCheck) {
        this.pointRepository = pointRepository;
        this.areaCheck = areaCheck;
    }

    @CrossOrigin
    @GetMapping("/api/getEntries")
    ResponseEntity<?> allPoints() {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.error("in getEntries");
        return new ResponseEntity<>(
                pointRepository.findAllById(user.getUsername()),
                HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/api/addEntry")
    ResponseEntity<?> newPoint(@RequestBody Point newPoint) {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newPoint.setResult(areaCheck.isInArea(newPoint));
        newPoint.setUsername(user.getUsername());
        logger.error("newPoint:" +newPoint.toString());
        return new ResponseEntity<>(
                pointRepository.save(newPoint),
                HttpStatus.OK);
    }

}
