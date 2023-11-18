/*SLIP 7: 
 * Write a Java Program to implement undo command to test Ceiling fan. 
 */
// CeilingFan class representing the receiver
class CeilingFan {
    private String speed;

    public CeilingFan() {
        speed = "Off";
    }

    public void high() {
        speed = "High";
        System.out.println("Ceiling Fan is on high speed.");
    }

    public void medium() {
        speed = "Medium";
        System.out.println("Ceiling Fan is on medium speed.");
    }

    public void low() {
        speed = "Low";
        System.out.println("Ceiling Fan is on low speed.");
    }

    public void off() {
        speed = "Off";
        System.out.println("Ceiling Fan is off.");
    }

    public String getSpeed() {
        return speed;
    }
}

// Command interface
interface Command {
    void execute();

    void undo();
}

// Concrete Command for High speed
class HighCommand implements Command {
    private CeilingFan ceilingFan;

    public HighCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        ceilingFan.high();
    }

    @Override
    public void undo() {
        ceilingFan.off();
    }
}

// Concrete Command for Medium speed
class MediumCommand implements Command {
    private CeilingFan ceilingFan;

    public MediumCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        ceilingFan.medium();
    }

    @Override
    public void undo() {
        ceilingFan.off();
    }
}

// Concrete Command for Low speed
class LowCommand implements Command {
    private CeilingFan ceilingFan;

    public LowCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        ceilingFan.low();
    }

    @Override
    public void undo() {
        ceilingFan.off();
    }
}

// Concrete Command for Undo
class UndoCommand implements Command {
    private Command lastCommand;

    public UndoCommand(Command lastCommand) {
        this.lastCommand = lastCommand;
    }

    @Override
    public void execute() {
        lastCommand.undo();
    }

    @Override
    public void undo() {
        // Undoing an undo command is not applicable
        System.out.println("Cannot undo an undo command.");
    }
}

// RemoteControlInvoker class representing the invoker
class RemoteControlInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Client class demonstrating the use of the Command Pattern
public class CeilingFanDemo {
    public static void main(String[] args) {
        CeilingFan ceilingFan = new CeilingFan();

        // Setting up commands
        Command highCommand = new HighCommand(ceilingFan);
        Command mediumCommand = new MediumCommand(ceilingFan);
        Command lowCommand = new LowCommand(ceilingFan);
        Command undoCommand = new UndoCommand(highCommand); // Initial undo command

        RemoteControlInvoker remoteControl = new RemoteControlInvoker();

        // Testing the ceiling fan with different commands
        remoteControl.setCommand(highCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(mediumCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(lowCommand);
        remoteControl.pressButton();

        // Undo the last command
        remoteControl.setCommand(undoCommand);
        remoteControl.pressButton();
    }
}
