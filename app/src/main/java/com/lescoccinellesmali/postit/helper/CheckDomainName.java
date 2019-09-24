/*
package com.lescoccinellesmali.postit.helper;

import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

*/
/**
 * Created by fallaye on 7/22/16.
 *//*

public class CheckDomainName {

    private static Pattern pDomainNameOnly;
    private static final String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

    static {
        pDomainNameOnly = Pattern.compile(DOMAIN_NAME_PATTERN);
    }

    public CheckDomainName(){}

    public static boolean isValidDomainName(String domainName) {
        return pDomainNameOnly.matcher(domainName).find();
    }

    public String getDomainName(String domainName){
        String hostName ="", tempStr = "", ipAddress = "";
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(domainName);
            tempStr = inetAddress.getHostName();
            if (tempStr.startsWith("www.")) {
                tempStr = tempStr.substring(4, tempStr.length());
            }
            for(int i = 0; i < tempStr.length(); i++){
                if(Character.isLetterOrDigit(tempStr.charAt(i))){
                    hostName += Character.toUpperCase(tempStr.charAt(i));
                }else{
                    hostName += "_";
                }
            }
            ipAddress = inetAddress.getHostAddress();
            //Toast.makeText(this, hostName + " " + ipAddress , Toast.LENGTH_LONG).show();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }
}
*/
