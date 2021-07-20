package automation;

public class Main {

    public static void main(String[] args) {
        Page Costco = new Page("https://costco.com", "chrome");
        Costco.closeTab();
        Costco.stop();
    }
}
