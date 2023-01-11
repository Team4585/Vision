package frc.robot;

public class VisionTeleopDecisionMaker {
  private VisionJoystick m_TheJoystick = new VisionJoystick();

  private Limelight m_Limelight;



  VisionTeleopDecisionMaker(){

  }

  public void initialize(){
  }

  public void doDecisions(){

    // System.out.println("-- F/B: " + m_TheJoystick.getForwardBackwardValue() + 
    //                    "   S/S: " + m_TheJoystick.getSideToSideValue() + 
    //                    "   Rot: " + m_TheJoystick.getTwistValue());


    if (m_TheJoystick.triggerPushed()){
      m_Limelight.getDegHorizontalFromTarget();
      //m_Limelight.toggleLED();
      
    }

    if (m_TheJoystick.button4Pushed()){
      //m_Limelight.getDegVerticalFromTarget();
      m_Limelight.toggleLED();
    }

    if (m_TheJoystick.button5Pushed()){
      m_Limelight.getSkew();
    }
  }

  public void setLimelightSubSystem(Limelight LimelightSys){
    m_Limelight = LimelightSys;
  }


}
