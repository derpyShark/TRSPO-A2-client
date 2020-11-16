package kpi.trspo.clientapp.testingutil;

public enum Surname {
    SMITH("Smith"),
    JOHNSON("Johnson"),
    MILLER("Miller"),
    WILLIAMS("Williams"),
    JONES("Jones"),
    BROWN("Brown"),
    DAVIS("Davis"),
    WILSON("Wilson"),
    MOORE("Moore"),
    TAYLOR("Taylor");

    private String surname;

    Surname(String surname) {
        this.surname = surname;
    }

    public String getSurname(){
        return surname;
    }
}
