package lab4.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "points")
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

}