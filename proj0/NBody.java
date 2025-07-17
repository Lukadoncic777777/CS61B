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

    public static void main(){

    }

}