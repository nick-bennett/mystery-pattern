package edu.cnm.deepdive.mysterypattern.model;

public class Position {

  private double x;
  private double y;

  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  protected void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  protected void setY(double y) {
    this.y = y;
  }

}
