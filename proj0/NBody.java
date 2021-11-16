public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int numPlanets = in.readInt();
        double uniRadius = in.readDouble();
        return uniRadius;
    }
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int numPlanets = in.readInt();
        double uniRadius = in.readDouble();
        Planet[] allPlanets = new Planet[numPlanets];
        for(int i = 0; i < numPlanets; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        /*Convert string to double @source https://www.geeksforgeeks.org/convert-string-to-double-in-java/*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double uniRadius = readRadius(filename);
        int size = 5;
        Planet[] uniPlanets = new Planet[size];
        uniPlanets = readPlanets(filename);

        for(int t=0; t < T; t+=dt){
            double[] xForces = new double[uniPlanets.length];
            double[] yForces = new double[uniPlanets.length];
            for(int i =0; i < uniPlanets.length; i++){
                xForces[i] = uniPlanets[i].calcNetForceExertedByX(uniPlanets);
                yForces[i] = uniPlanets[i].calcNetForceExertedByY(uniPlanets);
            }
            for(int i =0; i < uniPlanets.length; i++){
                uniPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            /*Drawing background*/
            StdDraw.setScale(-uniRadius, uniRadius);
            StdDraw.picture(0, 0, "images/starfield.jpg");
            StdDraw.enableDoubleBuffering();
            /*Drawing planets*/
            for(int i =0; i < uniPlanets.length; i++){
                uniPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", uniPlanets.length);
        StdOut.printf("%.2e\n", uniRadius);
        for (int i = 0; i < uniPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    uniPlanets[i].xxPos, uniPlanets[i].yyPos, uniPlanets[i].xxVel,
                    uniPlanets[i].yyVel, uniPlanets[i].mass, uniPlanets[i].imgFileName);
        }

    }
}
