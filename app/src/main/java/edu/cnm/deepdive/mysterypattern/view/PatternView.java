package edu.cnm.deepdive.mysterypattern.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.mysterypattern.R;
import edu.cnm.deepdive.mysterypattern.controller.MainFragment.Mode;
import edu.cnm.deepdive.mysterypattern.model.Position;
import edu.cnm.deepdive.mysterypattern.model.Terrain;
import java.util.List;

public class PatternView extends View {

  private List<Position> vertices;
  private Terrain terrain;
  private Bitmap bitmap;
  private Canvas canvas;
  private Paint paint;
  private RectF dest;
  private Mode mode;

  {
    setWillNotDraw(false);
    paint = new Paint();
    dest = new RectF();
  }

  public PatternView(Context context) {
    super(context);
  }

  public PatternView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public PatternView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public PatternView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @SuppressLint("CanvasSize")
  @Override
  protected void onDraw(Canvas canvas) {
    if (mode == Mode.BUILDING || mode == Mode.READY) {
      if (vertices != null) {
        paint.setColor(Color.BLUE);
        for (Position position : vertices) {
          canvas.drawCircle((float) position.getX(), (float) position.getY(),
              getContext().getResources().getDimension(R.dimen.vertex_radius), paint);
        }
      }
    } else {
      if (bitmap == null) {
        createBitmap();
      }
      dest.set(0, 0, canvas.getWidth(), canvas.getHeight());
      canvas.drawBitmap(bitmap, null, dest, null);
    }
  }

  @Override
  public boolean performClick() {
    return super.performClick();
  }

  public List<Position> getVertices() {
    return vertices;
  }

  public void setVertices(List<Position> vertices) {
    this.vertices = vertices;
  }

  public Terrain getTerrain() {
    return terrain;
  }

  public void setTerrain(Terrain terrain) {
    this.terrain = terrain;
  }

  public Mode getMode() {
    return mode;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  private void createBitmap() {
    bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
    canvas = new Canvas(bitmap);
    canvas.drawColor(Color.WHITE);
  }

  public void reset() {
    bitmap = null;
    canvas = null;
  }

  public void update() {
    if (bitmap == null) {
      createBitmap();
    }
    if (terrain != null) {
      paint.setColor(Color.BLACK);
      for (Position position : terrain.getCurrent()) {
        canvas.drawPoint((float) position.getX(), (float) position.getY(), paint);
      }
    }
  }

}
