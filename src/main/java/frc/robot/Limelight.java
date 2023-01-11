package frc.robot;

import frc.robot.huskylib.src.RoboDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends RoboDevice{

  private NetworkTable table;
  private boolean limelightIsConnected = false;
  public Limelight(){
    super("Limelight Sub System");
  }

  public void Initialize(){
    table = NetworkTableInstance.getDefault().getTable("limelight");
    //Change the modes if needed (look at limelight API for de)
    table.getEntry("<ledMode>").setNumber(3);
    table.getEntry("<camMode>").setNumber(0);
    table.getEntry("<stream>").setNumber(2);
    table.getEntry("<snapshot>").setNumber(1);
    table.getEntry("<pipeline>").setNumber(0);
  }

  @Override
  public void doGatherInfo() {
    super.doGatherInfo();

  }

  @Override
  public void doActions() {
    super.doActions();
  }

  public boolean isConnected() {
    System.out.println(limelightIsConnected);
    return limelightIsConnected;
  }

  public void toggleLED(){
    table = NetworkTableInstance.getDefault().getTable("limelight");

    System.out.println("Table: " + table);
    System.out.println("tx: " + table.getEntry("tx"));

    if (table.getEntry("ledMode").getDouble(0) == 0.0){
      table.getEntry("ledMode").setNumber(1);
    }
    else if (table.getEntry("ledMode").getDouble(1) == 1.0){
      table.getEntry("ledMode").setNumber(0);
    }
  }

  //make sure to set the target
  public boolean isTargetFound() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    
      NetworkTableEntry tv = table.getEntry("tv");
      double v = tv.getDouble(0);
      if (v == 0.0f) {
        return false;
      } else {
        return true;
      }
  }

  //tx horizontal offset from crosshair to target (-27 to 27 degrees)
  public double getDegHorizontalFromTarget() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0.0);
    
    if (tx != null){
      System.out.println("Degrees Horizontal: " + x);
    }

    return x;
  }

  //ty vertical offset from crosshair to target (-20.5 to 20.5 degrees)
  public double getDegVerticalFromTarget() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double y = ty.getDouble(0.0);
    
    System.out.println("Degrees Vertical: " + y);

    return y;
  }
  
  //ta target area (0% to 100% of the of the image)
  public double getTargetArea() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ta = table.getEntry("ta");
    double a = ta.getDouble(0.0);
    return a;
  }

  //ts shew or rotation (-90 to 90 degrees)
  public double getSkew() { //Used to be getShewOrRotation. That didn't make sense because the docs say it is skew but the method name was ...ShewOr...
  table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ts = table.getEntry("ts");
    double s = ts.getDouble(0.0);
    
    System.out.println("Degrees Skew: " + s);

    return s;
  }

  //tl the pipeline's latency contribution (ms); add at least 11 ms for image capture latency
  public double getPipelineLatency() {
    NetworkTableEntry tl = table.getEntry("tl");
    double pl = tl.getDouble(0.0);
    return pl;
  }

  //Set pipeline from 0-9
  public void setPipeline(Integer pipeline) {
    if (pipeline < 0) {
      pipeline = 0;
      throw new IllegalArgumentException("Illegal pipeline: the pipeline can't be less than zero");
    } else if (pipeline > 9) {
      pipeline = 0;
      throw new IllegalArgumentException("Illegal pipeline: the pipeline can't be greater than 9");
    }
    table.getEntry("pipeline").setValue(pipeline);
  }

  //Returns current pipeline index
  public double getPipelineIndex() {
    NetworkTableEntry getpipe = table.getEntry("getpipe");
    double pipeLine = getpipe.getDouble(0.0);
    return pipeLine;
  }

}
