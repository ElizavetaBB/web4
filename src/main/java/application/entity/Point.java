package application.entity;

import javax.persistence.*;

@Entity
@Table(name="points")
public class Point {
    private @Id @GeneratedValue Long id;
    private Double x;
    private Double y;
    private Double r;
    private Boolean result;
    @ManyToOne(targetEntity = User.class)
    private String username;
    public Point() {}

    public Point(double x, double y, double r, boolean result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    public String toString() {
        return String.format("%s %s %s %s", x, y, r, result);
    }

    public Double getX(){return x;}

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public Boolean getResult(){return result;}

    public void setResult(Boolean result){this.result=result;}

    public void setUsername(String username){this.username=username;}
}
