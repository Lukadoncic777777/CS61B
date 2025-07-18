import static java.lang.Math.sqrt;

public class Planet {
    public double xxPos,yyPos,xxVel,yyVel,mass;
    public String imgFileName;
    private static final double G=6.67*1e-11;
    public Planet(double xP,double yP,double xV,double yV,double ms,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=ms;
        imgFileName=img;
    }
    public Planet(Planet p){
        this.xxPos=p.xxPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.yyPos=p.yyPos;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx=p.xxPos-xxPos,dy=p.yyPos-yyPos;
        return sqrt(dx*dx+dy*dy);
    }

    public double calcForceExertedBy(Planet p){
        double dis=this.calcDistance(p);
        return G*mass*p.mass/(dis*dis);
    }

    public double calcForceExertedByX(Planet p){
        double dx=p.xxPos-xxPos;
        double dis=this.calcDistance(p);
        double F=this.calcForceExertedBy(p);
        return F*dx/dis;
    }

    public double calcForceExertedByY(Planet p){
        double dy=p.yyPos-yyPos;
        double dis=this.calcDistance(p);
        double F=this.calcForceExertedBy(p);
        return F*dy/dis;
    }

    public double calcNetForceExertedByX(Planet[] other){
        double res=0;
        for(Planet planet:other){
            res+=this.calcForceExertedByX(planet);
        }
        return res;
    }
    public double calcNetForceExertedByY(Planet[] other){
        double res=0;
        for(Planet planet:other){
            res+=this.calcForceExertedByY(planet);
        }
        return res;
    }
    public double calcNetForceExertedByXY(Planet[] other){
        double fX=this.calcNetForceExertedByX(other);
        double fY=this.calcNetForceExertedByY(other);
        return sqrt(fX*fX+fY*fY);
    }
    public void update(double dt, double fX,double fY){
        xxPos+=xxVel*dt+0.5*fX/mass*dt*dt;
        yyPos+=yyVel*dt+0.5*fY/mass*dt*dt;
        xxVel+=dt*fX/mass;
        yyVel+=dt*fY/mass;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}
