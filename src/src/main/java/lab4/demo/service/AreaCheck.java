package lab4.demo.service;
import lab4.demo.entity.Point;
import org.springframework.stereotype.Component;

@Component
public class AreaCheck {

    private boolean check(Double x, Double y, Double r) {
        boolean triangle = x <= 0 && y <= 0 && y >= -2*x-r;
        boolean square = x >= 0 && y <= 0 && x <= r && y >= -r/2;
        boolean sector = x <= 0 && y >= 0 && (x*x + y*y) <= r*r;
        return triangle || square || sector;
    }
    public boolean isInArea(Double x, Double y, Double r) {
        return check(x,y,r);
    }
    public boolean isInArea(Point point) {
        return check(point.getX(),point.getY(),point.getR());
    }

    public boolean isGood(Point point){
        double x=point.getX();double y=point.getY();double r=point.getR();
        if (x>7 || x<-7) return false;
        if (y>7 || y<-7) return false;
        if (r<0) return false;
        boolean rr=false;
        for (int i=0;i<6;i++){
            if(r==i) {rr=true;break;}
        }
        return rr;
    }
}
