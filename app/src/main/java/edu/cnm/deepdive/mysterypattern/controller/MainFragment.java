package edu.cnm.deepdive.mysterypattern.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cnm.deepdive.mysterypattern.R;
import edu.cnm.deepdive.mysterypattern.model.Position;
import edu.cnm.deepdive.mysterypattern.model.Terrain;
import edu.cnm.deepdive.mysterypattern.view.PatternView;
import java.util.LinkedList;
import java.util.List;

public class MainFragment extends Fragment implements View.OnTouchListener {

  private static final int NUM_VERTICES = 3;
  private static final double JUMP_FRACTION = 0.5;

  private List<Position> vertices;
  private PatternView patternView;
  private Mode mode;
  private Terrain terrain;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    patternView = view.findViewById(R.id.pattern_view);
    patternView.setOnTouchListener(this);
    vertices = new LinkedList<>();
    patternView.setVertices(vertices);
    build();
    return view;
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.options, menu);
  }

  @Override
  public void onPrepareOptionsMenu(@NonNull Menu menu) {
    super.onPrepareOptionsMenu(menu);
    menu.findItem(R.id.play).setVisible(mode == Mode.READY || mode == Mode.PAUSED);
    menu.findItem(R.id.pause).setVisible(mode == Mode.JUMPING);
    menu.findItem(R.id.reset).setVisible(mode == Mode.PAUSED);
    menu.findItem(R.id.build).setVisible(mode == Mode.PAUSED);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    // TODO Check mode.
    if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
      vertices.add(new Position(event.getX(), event.getY()));
      if (vertices.size() >= NUM_VERTICES) {
        mode = Mode.READY;
        getActivity().invalidateOptionsMenu();
        while (vertices.size() > NUM_VERTICES) {
          vertices.remove(0);
        }
      }
      // TODO Refresh options menu.
      patternView.invalidate();
    }
    v.performClick();
    return false;
  }

  @SuppressLint("ShowToast")
  private void build() {
    Toast.makeText(getContext(), "Click to place the vertices of a triangle.", Toast.LENGTH_LONG).show();
    mode = Mode.BUILDING;
    vertices.clear();
    // TODO Reset the patternView.
    // TODO Reset the terrain.
  }

  public enum Mode {
    BUILDING, READY, JUMPING, PAUSED
  }

}
