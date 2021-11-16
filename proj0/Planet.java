public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private double G = 6.67e-11;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        return G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
    }
    public double calcForceExertedByX(Planet p){
        double Fx = calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
        return Fx;
    }
    public double calcForceExertedByY(Planet p){
        double Fy = calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
        return Fy;
    }
    public double calcNetForceExertedByX(Planet[] p){
        double netForce = 0;
        for(Planet x: p){
            if(this.equals(x)){
                continue;
            }
            netForce += calcForceExertedByX(x);
        }
        return netForce;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double netForce = 0;
        for(Planet x: p){
            if(this.equals(x)){
                continue;
            }
            netForce += calcForceExertedByY(x);
        }
        return netForce;
    }
    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
