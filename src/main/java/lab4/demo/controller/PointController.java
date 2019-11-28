package lab4.demo.controller;

import lab4.demo.dao.PointRepository;
import lab4.demo.entity.Point;
import lab4.demo.service.AreaCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class PointController {
    private final PointRepository pointRepository;
    private final AreaCheck areaCheck;

    PointController(PointRepository pointRepository, AreaCheck areaCheck) {
        this.pointRepository = pointRepository;
        this.areaCheck = areaCheck;
    }

    @CrossOrigin
    @GetMapping("/api/getEntries")
    ResponseEntity<?> allPoints(Principal user) {
        System.out.println("all points request from "+user.getName());
        return new ResponseEntity<>(
                pointRepository.findAllById(user.getName()),
                HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/api/addEntry")
    ResponseEntity<?> newPoint(@RequestBody Point newPoint, Principal user) {
        newPoint.setResult(areaCheck.isInArea(newPoint));
        newPoint.setUsername(user.getName());
        return new ResponseEntity<>(
                pointRepository.save(newPoint),
                HttpStatus.OK);
    }

}
