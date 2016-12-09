package medium;

public class ReflectionObject {

    private String privateName;

    private String anotherPrivateName;

    private String outputString;

    public String getOutputString() {
        return outputString;
    }

    public void setOutputString(String outputString) {
        this.outputString = outputString;
    }

    private String privateMethod() {
        return "This is a private method!";
    }

    ReflectionObject(int number, String str) {
        this.outputString = "You send:" + number + " " + str;
    }
}
