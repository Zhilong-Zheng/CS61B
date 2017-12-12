public class NBody {
   public static double readRadius(String txt) {
      In in = new In(txt);
      int planetnum = in.readInt();
      double radius = in.readDouble();
      return radius;
   }
   
   public static Planet[] readPlanets(String txt) {
      In in = new In(txt);
      int planetnum = in.readInt();
      double radius = in.readDouble();
      Planet[] ps = new Planet[planetnum];
      for(int i = 0; i < planetnum; i++) {
           double xxPos = in.readDouble();
           double yyPos = in.readDouble();
           double xxVel = in.readDouble();
           double yyVel = in.readDouble();
           double mass = in.readDouble();
           String img = in.readString();
           Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
           ps[i] = planet;
    /*     ps[i].xxPos = in.readDouble();
           ps[i].yyPos = in.readDouble();
           ps[i].xxVel = in.readDouble();
           ps[i].yyVel = in.readDouble();
           ps[i].mass = in.readDouble();
           ps[i].imgFileName = in.readString();*/
      }
      return ps;
   }
   
   public static void main(String[] args) {
      Double T = Double.parseDouble(args[0]);
      Double dt = Double.parseDouble(args[1]);
      String filename = args[2];
      
      double radius = readRadius("./data/planets.txt");
      StdDraw.setScale(-radius, radius);
      StdDraw.clear();
      StdDraw.picture(0, 0, "./images/starfield.jpg");
      
      Planet[] planets = NBody.readPlanets("./data/planets.txt");
      for(int i = 0; i < planets.length; i++) {
         planets[i].draw();
      }
      
      double time = 0;
      while(time != T) {
         StdDraw.clear();
         StdDraw.picture(0, 0, "./images/starfield.jpg");
         double[] xForce = new double[planets.length];
         double[] yForce = new double[planets.length];
         for(int i = 0; i < planets.length; i++) {
            xForce[i] = planets[i].calcNetForceExertedByX(planets);
            yForce[i] = planets[i].calcNetForceExertedByY(planets);
            
            planets[i].update(time, xForce[i], yForce[i]);
            planets[i].draw();
         }
         StdDraw.show(10);
         time += dt;      
      }
   }
}  
