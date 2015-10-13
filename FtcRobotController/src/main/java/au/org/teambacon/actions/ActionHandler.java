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
        BRobot.Instance.telemetry.addData("Step", this.Step);
        switch (this.Step) {
            case 0:
                this.ActionList.get(0).init();
                this.Step++;
                break;
            case 1:
                this.ActionList.get(0).start();
                this.Step++;
                break;
            case 2:
                if (this.ActionList.get(0).loop())
                    this.Step++;
                break;
            case 3:
                this.ActionList.get(0).end();
                this.Step++;
                break;
            case 4:
                this.ActionList.remove(0);
                this.Step = 0;
                break;
        }
    }

    public void registerAction(Action action) {
        this.ActionList.add(action);
    }
}
