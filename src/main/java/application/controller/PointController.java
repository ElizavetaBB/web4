package application.controller;

import application.dao.PointRepository;
import application.entity.Point;
import application.entity.Area;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
public class PointController {

    private final PointRepository pointRepository;
    private final Area area;

    PointController(PointRepository pointRepository, Area area) {
        this.pointRepository = pointRepository;
        this.area = area;
    }

    @CrossOrigin
    @PostMapping("api/addEntry")
    ResponseEntity<?> newPoint(@RequestBody Point newPoint, Principal user) {
        if (newPoint.getR()<0 || newPoint.getR()>5 || newPoint.getX()<-3 || newPoint.getX()>5 || newPoint.getY()<-3 || newPoint.getY()>5) {
            return new ResponseEntity<>("Radius must be nonnegative or X,Y more than 5 and less than -3",
                    HttpStatus.CONFLICT);
        }
        newPoint.setUsername(user.getName());
        return new ResponseEntity<>(
                pointRepository.save(newPoint),
                HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("api/getEntries")
    ResponseEntity<?> allPoints(Principal user) {
        System.out.println("all points request from "+user.getName());
        return new ResponseEntity<>(
                pointRepository.findAllUserPointsById(user.getName()),
                HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("api/addEntry")
    ResponseEntity<?> newPoints(@RequestParam(value = "x") Double x, @RequestParam(value = "y") Double y, @RequestParam(value = "r") Double r,Principal user) {
        if (r<0 || r>5 || x<-3 || x>5 || y<-3 || y>5) {
            return new ResponseEntity<>("Radius must be nonnegative or X,Y more than 5 and less than -3",
                    HttpStatus.CONFLICT);
        }
        Point point=new Point(x,y,r,area.isInArea(x,y,r));
        point.setUsername(user.getName());
        return new ResponseEntity<>(
                pointRepository.save(point),
                HttpStatus.OK);
    }

}
