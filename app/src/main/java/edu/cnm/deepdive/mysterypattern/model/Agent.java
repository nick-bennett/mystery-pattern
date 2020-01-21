package edu.cnm.deepdive.mysterypattern.model;

import java.util.Random;

public class Agent extends Position {

  private static Random rng = new Random();

  public Agent(double x, double y) {
    super(x, y);
  }

  public void jump(Position[] vertices, double fraction) {
    Position target = vertices[rng.nextInt(vertices.length)];
    setX(getX() + (target.getX() - getX()) * fraction);
    setY(getY() + (target.getY() - getY()) * fraction);
  }

}
