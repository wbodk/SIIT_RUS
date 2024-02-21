package primer08ini;
public class TestIni {
  public TestIni() {
    INIFile ini = new INIFile("test.ini");
    String title = ini.getString("General", "Title", "nemaaaaa");
    int x = ini.getInt("General", "x", -1);
    System.out.println("U sekciji 'General', vrednost 'Title': " + title);
    System.out.println("U sekciji 'General', vrednost 'x': " + x);
    
    x = ini.getInt("User", "x", -1);
    System.out.println("U sekciji 'User', vrednost 'x': " + x);
    
    double d = ini.getDouble("User", "d", -1.5);
    System.out.println("U sekciji 'User', vrednost 'd': " + d);
    System.out.println("U sekciji 'User', vrednost 'title': " + ini.getString("User", "Title", "nemaaa"));
  }

  public static void main(String[] args) {
    new TestIni();
  }
}