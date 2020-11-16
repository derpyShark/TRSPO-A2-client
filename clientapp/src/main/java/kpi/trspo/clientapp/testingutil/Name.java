package kpi.trspo.clientapp.testingutil;

public enum Name {
    LIAM("Liam"),
    NOAH("Noah"),
    JOHN("John"),
    JAMES("James"),
    WILLIAM("William"),
    BENJAMIN("Benjamin"),
    JASON("Jason"),
    MASON("Mason"),
    JACOB("Jacob"),
    LUCAS("Lucas"),
    DANIEL("Daniel");

    private String name;

    public String getName(){
        return name;
    }

    Name(String name) {
        this.name = name;
    }

}
