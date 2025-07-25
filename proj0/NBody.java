import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NBody {
    public static double readRadius(String filepath){
        In data = new In(filepath);
        data.readInt();
        double radius=data.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filepath){
        In data = new In(filepath);
        int n=data.readInt();
        Planet[] planets=new Planet[n];
        data.readDouble();
        for(int i=0;i<n;i++)
        {
            planets[i]=new Planet(0,0,0,0,0,"");
            planets[i].xxPos=data.readDouble();
            planets[i].yyPos=data.readDouble();
            planets[i].xxVel=data.readDouble();
            planets[i].yyVel=data.readDouble();
            planets[i].mass=data.readDouble();
            planets[i].imgFileName=data.readString();
        }
        return planets;
    }

    public static void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius=NBody.readRadius(filename);
        Planet[] planets=NBody.readPlanets(filename);
        int n=planets.length;
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
//        System.out.println(T+" "+dt);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet planet:planets) {
            planet.draw();
        }
        StdDraw.show();
        for(double i=0;i<T;i+=dt)
        {
//            System.out.println(i);
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for(int j=0;j<n;j++)
            {
                xForces[j]=planets[j].calcNetForceExertedByX(planets);
                yForces[j]=planets[j].calcNetForceExertedByY(planets);
            }
            for(int j=0;j<n;j++)
            {
                planets[j].update(dt,xForces[j],yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet:planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        System.out.println(n);
        System.out.println(radius);
        for(int i=0;i<n;i++){
            System.out.println(planets[i].xxPos+" "+planets[i].yyPos+" "+planets[i].xxVel+" "+planets[i].yyVel+" "+planets[i].mass+" "+planets[i].imgFileName);
        }
    }

}