package application.controller;

import application.dao.PointRepository;
import application.entity.Point;
import application.entity.Area;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
public class PointController {

    private final PointRepository pointRepository;
    private final Area area;

    PointController(PointRepository pointRepository, Area area) {
        this.pointRepository = pointRepository;
        this.area = area;
    }

    @PostMapping("api/addEntry")
    ResponseEntity<?> newPoint(@RequestBody Point newPoint, Principal user) {
        newPoint.setResult(area.isInArea(newPoint));
        newPoint.setUsername(user.getName());
        return new ResponseEntity<>(
                newPoint,
                HttpStatus.OK);
    }


    @RequestMapping("api/getEntries")
    ResponseEntity<?> allPoints(Principal user) {
        System.out.println("all points request from "+user.getName());
        return new ResponseEntity<>(
                pointRepository.findAllUserPointsById(user.getName()),
                HttpStatus.OK);
    }

    @GetMapping("api/addEntry")
    ResponseEntity<?> newPoints(@RequestParam(value = "x") Double x, @RequestParam(value = "y") Double y, @RequestParam(value = "r") Double r,Principal user) {
        Point point=new Point(x,y,r,area.isInArea(x,y,r));
        point.setUsername(user.getName());
        return new ResponseEntity<>(
                point,
                HttpStatus.OK);
    }

}
