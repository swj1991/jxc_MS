package tableModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Check {
      public static boolean checkItem(String str,String check){
    	  boolean checkup = false;
    	  Pattern pattern = Pattern.compile(str);
    	  Matcher matcher = pattern.matcher(check);
    	  checkup = matcher.matches();
    	  return checkup;
      }
      
      public static boolean checkPhoneNumber(String phonenumber){
    	  String reg = "^[1][\\d]{10}|\\d{4}-\\d{7,8}|\\d{3}-\\d{8}";
    	  return checkItem(reg,phonenumber);
      }
      
      public static boolean checkEmail(String email){
    	  String reg = "\\w+\\@\\w+\\.\\w{2,}";
    	  return checkItem(reg,email);
      }
      
      public static boolean checkPostcode(String postcode){
    	  String reg = "^[1-9]\\d{5}";
    	  return checkItem(reg,postcode);
      }
      
      public static boolean checkIDCard(String idcard){
    	  String reg = "\\d{17}[\\d|X]|\\d{14}[\\d|X]";
    	  return checkItem(reg,idcard);
      }
      
//      public static void main(String[] args) {
//    	  if(checkIDCard("420606199109043056")){
//    		  System.out.println("successful");
//    	  }else{
//    		  System.out.println("failed!");
//    	  }
//    	  
//      }
      
}
