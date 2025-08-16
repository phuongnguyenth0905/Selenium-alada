package webdriver;

public class T09_System_Property {
public static void main(String[] args) {
	String projectFolder=System.getProperty("user.dir");
	System.out.println(projectFolder);
	System.out.println(projectFolder+"\\autoIT\\authen_firefox.exe");
}
}
