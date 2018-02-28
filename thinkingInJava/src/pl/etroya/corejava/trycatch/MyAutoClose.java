package pl.etroya.corejava.trycatch;

public class MyAutoClose implements AutoCloseable {
    public void saySomething() {
        System.out.println("Something");

    }

    @Override
    public void close() throws Exception {
        System.out.println("close");

    }
}
