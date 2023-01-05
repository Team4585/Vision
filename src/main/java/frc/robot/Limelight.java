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
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    //Change the modes if needed (look at limelight API for de)
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<ledMode>").setNumber(3);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<camMode>").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<stream>").setNumber(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<snapshot>").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<pipeline>").setNumber(0);
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
    return limelightIsConnected;
  }

  //make sure to set the target
  public boolean isTargetFound() {
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
    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0.0);
    return x;
  }

  //ty vertical offset from crosshair to target (-20.5 to 20.5 degrees)
  public double getDegVerticalFromTarget() {
    NetworkTableEntry ty = table.getEntry("ty");
    double y = ty.getDouble(0.0);
    return y;
  }
  
  //ta target area (0% to 100% of the of the image)
  public double getTargetArea() {
    NetworkTableEntry ta = table.getEntry("ta");
    double a = ta.getDouble(0.0);
    return a;
  }

  //ts shew or rotation (-90 to 90 degrees)
  public double getShewOrRotation() {
    NetworkTableEntry ts = table.getEntry("ts");
    double s = ts.getDouble(0.0);
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
