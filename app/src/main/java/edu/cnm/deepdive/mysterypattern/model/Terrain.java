package edu.cnm.deepdive.mysterypattern.model;

import java.util.Arrays;
import java.util.List;

public class Terrain {

  private final Position[] vertices;
  private final double fraction;
  private Agent[] agents;

  public Terrain(List<Position> vertices, double fraction) {
    this.vertices = vertices.toArray(new Position[0]);
    this.fraction = fraction;
    reset();
  }

  public void reset() {
    agents = new Agent[vertices.length];
    for (int i = 0; i < vertices.length; i++) {
      agents[i] = new Agent(vertices[i].getX(), vertices[i].getY());
    }
  }
  
  public void update() {
    for (Agent agent : agents) {
      agent.jump(vertices, fraction);
    }
  } 
  
  public Position[] getCurrent() {
    return Arrays.copyOf(agents, agents.length);
  }
  
}
