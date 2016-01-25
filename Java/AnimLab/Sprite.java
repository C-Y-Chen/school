import java.awt.Image;
import java.awt.Rectangle;

public class Sprite
{
  private Animation anim;
  private float x;
  private float y;
  private float dx;
  private float dy;
  private int width;
  private int height;
  private int hit;
  
  public Sprite(Animation anim)
  {
    this.anim = anim;
    setHit(0);
  }
  
  public void update(long elapsedTime)
  {
    this.x += this.dx * (float)elapsedTime;
    this.y += this.dy * (float)elapsedTime;
    this.anim.update(elapsedTime);
  }
  
  public float getX()
  {
    return this.x;
  }
  
  public float getY()
  {
    return this.y;
  }
  
  public void setX(float x)
  {
    this.x = x;
  }
  
  public void setY(float y)
  {
    this.y = y;
  }
  
  public int getWidth()
  {
    return this.anim.getImage().getWidth(null);
  }
  
  public int getHeight()
  {
    return this.anim.getImage().getHeight(null);
  }
  
  public Rectangle getRect(int h,int w) {
	  return new Rectangle((int)this.getX(), (int)this.getY(), h, w);
  }
  
  public float getVelocityX()
  {
    return this.dx;
  }
  
  public float getVelocityY()
  {
    return this.dy;
  }
  
  public void setVelocityX(float dx)
  {
    this.dx = dx;
  }
  
  public void setVelocityY(float dy)
  {
    this.dy = dy;
  }
  
  public int getDrawnWidth()
  {
    return this.width;
  }
  
  public void setDrawnWidth(int width)
  {
    this.width = width;
  }
  
  public int getDrawnHeight()
  {
    return this.height;
  }
  
  public void setDrawnHeight(int height)
  {
    this.height = height;
  }
  
  public int getHit()
  {
    return this.hit;
  }
  
  public void setHit(int hit)
  {
    this.hit = hit;
  }
  
  public Image getImage()
  {
    return this.anim.getImage();
  }
  
  public Animation getAnimation()
  {
    return this.anim;
  }
}
