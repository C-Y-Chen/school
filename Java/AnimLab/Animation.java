import java.awt.Image;
import java.util.ArrayList;

public class Animation
{
  private ArrayList<Animation.AnimFrame> frames;
  private int currFrameIndex;
  private long animTime;
  private long totalDuration;
  private boolean circulatedPlay;
  
  public Animation()
  {
    this.frames = new ArrayList();
    this.totalDuration = 0L;
    this.circulatedPlay = true;
    start();
  }
  
  public void setCirculatedPlay(boolean circulated)
  {
    this.circulatedPlay = circulated;
  }
  
  public synchronized void addFrame(Image image, long duration)
  {
    this.totalDuration += duration;
    this.frames.add(new Animation.AnimFrame(image, this.totalDuration));
  }
  
  public synchronized void start()
  {
    this.animTime = 0L;
    this.currFrameIndex = 0;
  }
  
  public synchronized void update(long elapsedTime)
  {
    if (this.frames.size() >= 1)
    {
      this.animTime += elapsedTime;
      if (this.animTime >= this.totalDuration) {
        if (this.circulatedPlay)
        {
          this.animTime %= this.totalDuration;
          this.currFrameIndex = 0;
        }
        else
        {
          this.currFrameIndex = -1;
        }
      }
      while (this.animTime > getFrame(this.currFrameIndex).endTime) {
        this.currFrameIndex += 1;
      }
    }
  }
  
  public synchronized Image getImage()
  {
    if ((this.frames.size() == 0) || (this.currFrameIndex < 0)) {
      return null;
    }
    return getFrame(this.currFrameIndex).image;
  }
  
  private Animation.AnimFrame getFrame(int i)
  {
    return (Animation.AnimFrame)this.frames.get(i);
  }
  
  private class AnimFrame
  {
    Image image;
    long endTime;
    
    public AnimFrame(Image image, long endTime)
    {
      this.image = image;
      this.endTime = endTime;
    }
  }
}
