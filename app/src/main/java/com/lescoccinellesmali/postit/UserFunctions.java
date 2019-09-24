package com.lescoccinellesmali.postit;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class UserFunctions {
    private JSONParser jsonParser;
    /*private static String loginURL = "http://10.0.2.2/~faldiall/go-mobile/POSTITPHP/login.php";
    private static String setUpSchoolURL = "http://10.0.2.2/~faldiall/go-mobile/POSTITPHP/setup_school.php";
*/
    //private static String loginURL = "http://10.0.2.2/go-mobile/POSTITPHP/login.php";
    //private static String setUpSchoolURL = "http://10.0.2.2/go-mobile/POSTITPHP/setup_school.php";

    private static String loginURL = "http://www.fallaye.com/login.php";
    private static String setUpSchoolURL = "http://www.fallaye.com/setup_school.php";

    private static String login_tag = "login";
    private static String register_tag = "register";
    private static String update_tag = "update";
    private static String delete_tag = "delete";

    public UserFunctions(){
        jsonParser = new JSONParser();
    }

    public JSONObject validateDomain(String domainName){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("domain_name", domainName));
        Log.d("UserFunctions", "Before Validate");
        JSONObject json = jsonParser.getJSONFromUrl(setUpSchoolURL, params);
        Log.d("UserFunctions After", json.toString());
        return json;
    }
    public JSONObject loginUser(String domainName, String email, String password){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("domain_name", domainName));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        return json;
    }
    public JSONObject registerUser(String name, String phone, String email, String password, String domainName){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("phone", phone));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("domain_name", domainName));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        return json;
    }
    public JSONObject updateUser(String domainName, String userTrackByEmail, String phone, String password){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", update_tag));
        params.add(new BasicNameValuePair("userTrackByEmail", userTrackByEmail));
        params.add(new BasicNameValuePair("phone", phone));
        params.add(new BasicNameValuePair("domain_name", domainName));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        return json;
    }
    public JSONObject deleteUser(String domainName, String userTrackByEmail){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", delete_tag));
        params.add(new BasicNameValuePair("domain_name", domainName));
        params.add(new BasicNameValuePair("userTrackByEmail", userTrackByEmail));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        return json;
    }
}