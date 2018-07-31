import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args) {
//        СДЕЛАЙ ВВОД С КОНСОЛЯ
        Scanner sc = new Scanner(System.in);

        double [] a = new double[2];
        a[0] =sc.nextDouble();
        a[1] =sc.nextDouble();
        double [] b = new double[2];
        b[0] =sc.nextDouble();
        b[1] =sc.nextDouble();
        Rectangle A = new Rectangle(a, b);
        double [] c = new double[2];
        c[0] =sc.nextDouble();
        c[1] =sc.nextDouble();
        double [] d = new double[2];
        d[0] =sc.nextDouble();
        d[1] =sc.nextDouble();
        Rectangle B = new Rectangle(c, d);
        System.out.println(A.commonSquare(A, B));

    }
}

class Rectangle {

    double [] RightUp =  new double[2];
//    double [] RightDown = new double[2];
    double [] LeftDown = new double[2];
//    double [] LeftUp = new double[2];
    double Square = 0;
    public Rectangle(double[] a, double[] b) {
        this.RightUp =  a;
//        double [] RightDown = {b[0],a[1]};
        this.LeftDown = b;
//        double [] LeftUp = {a[0], b[1]};
        this.Square = Math.abs(a[0]-b[0]) * Math.abs(a[1]-b[1]);
    }

    public double commonSquare(Rectangle a, Rectangle b) {
        double rect1L = Math.min(a.LeftDown[0], a.RightUp[0]);
        double rect1R = Math.max(a.LeftDown[0], a.RightUp[0]);
        double rect1B = Math.min(a.LeftDown[1], a.RightUp[1]);
        double rect1T = Math.max(a.LeftDown[1], a.RightUp[1]);

        double rect2L = Math.min(b.LeftDown[0], b.RightUp[0]);
        double rect2R = Math.max(b.LeftDown[0], b.RightUp[0]);
        double rect2B = Math.min(b.LeftDown[1], b.RightUp[1]);
        double rect2T = Math.max(b.LeftDown[1], b.RightUp[1]);

        if ((rect1B >= rect2T) | (rect2B >= rect1T) | (rect1L >= rect2R) | (rect2L >= rect1R)) {
            return 0;
        } else {
            double l = Math.max(rect1L, rect2L);
            double r = Math.min(rect1R, rect2R);
            double t = Math.min(rect1T, rect2T);
            double bb = Math.max(rect1B, rect2B);
            return (r-l)*(t-bb);
        }

//        double [ ] x = {}

    }

}