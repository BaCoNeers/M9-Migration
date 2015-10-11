package au.org.teambacon.actions;

import java.util.ArrayList;

import au.org.teambacon.wrapper.BRobot;

public class ActionHandler {
    public static BRobot Robot;

    public ArrayList<Action> ActionList = new ArrayList<Action>();

    private int Step = 0;

    public ActionHandler(BRobot instance) {
        this.Robot = instance;
    }

    public void handle() {
        BRobot.Instance.telemetry.addData("Size", this.ActionList.size());
        BRobot.Instance.telemetry.addData("step", this.Step);
        switch (this.Step) {
            case 0:
                this.ActionList.get(0).init();
                BRobot.Instance.telemetry.addData("Case", 0);
                this.Step++;
                break;
            case 1:
                this.ActionList.get(0).start();
                BRobot.Instance.telemetry.addData("Case", 1);
                this.Step++;
                break;
            case 2:
                BRobot.Instance.telemetry.addData("Case", 2);
                if (this.ActionList.get(0).loop())
                    this.Step++;
                break;
            case 3:
                BRobot.Instance.telemetry.addData("Case", 3);
                this.ActionList.get(0).end();
                this.Step++;
                break;
            case 4:
                BRobot.Instance.telemetry.addData("Case", 4);
                this.ActionList.remove(0);
                this.Step = 0;
                BRobot.flush(); // clear everything
                break;
        }
    }

    public void registerAction(Action action) {
        this.ActionList.add(action);
    }
}
